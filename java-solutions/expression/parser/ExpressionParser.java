package expression.parser;
import expression.*;

public class ExpressionParser extends BaseParser implements TripleParser {

    public ExpressionParser() {
        super(new StringSource(""));
    }

    private ExpressionParser(String source) {
        super(new StringSource(source));
    }

    @Override
    public ExpressionM parse(String expression) {
        return new ExpressionParser(expression).parseExpression();
    }

    private ExpressionM parseExpression() {
        ExpressionM result = parseAddSub();

        while (true) {
            skipWS();
            if (take('<')) {
                expect('<');
                result = new ShiftLeft(result, parseAddSub());
            } else if (take('>')) {
                if (take('>')) {
                    if (take('>')) {
                        result = new ShiftLogical(result, parseAddSub());
                    } else {
                        result = new ShiftRight(result, parseAddSub());
                    }
                } else {
                    throw error("Expected '>' or '>>'");
                }
            } else {
                return result;
            }
        }
    }

    private ExpressionM parseAddSub() {
        ExpressionM result = parseTerm();
        while (true) {
            skipWS();
            if (take('+')) {
                result = new Add(result, parseTerm());
            } else if (take('-')) {
                result = new Subtract(result, parseTerm());
            } else {
                return result;
            }
        }
    }

    private ExpressionM parseTerm() {
        ExpressionM result = parseFactor();
        while (true) {
            skipWS();
            if (take('*')) {
                result = new Multiply(result, parseFactor());
            } else if (take('/')) {
                result = new Divide(result, parseFactor());

            } else {
                return result;
            }
        }
    }

    private ExpressionM parseFactor() {
        skipWS();

        if (take('-')) {
            if (between('0','9')) {
                return parseNumber("-");
            }
            return new Negate(parseFactor());
        }

        if (take('(')) {
            ExpressionM result = parseExpression();
            expect(')');
            return result;
        }

        if (between('x', 'z')) {
            String name = String.valueOf(take());
            return new Variable(name);
        }

        if (between('0', '9')) {
            return parseNumber("");
        }

        throw error("Unexpected character: '" + ch + "'");
    }

    private Const parseNumber(String prefix) {
        StringBuilder sb = new StringBuilder(prefix);
        while (between('0', '9')) {
            sb.append(take());
        }
        try {
            return new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw error("Invalid number: " + sb);
        }
    }

    private void skipWS() {
        while (ch != END && Character.isWhitespace(ch)) {
            take();
        }
    }
}