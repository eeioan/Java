package expression.exceptions;
import expression.*;
import expression.parser.ExpressionM;
import expression.parser.StringSource;

public class ExpressionParser extends BaseParser implements TripleParser {

    public ExpressionParser() {
        super(new StringSource(""));
    }

    private ExpressionParser(String source) {
        super(new StringSource(source));
    }

    @Override
    public ExpressionM parse(String expression) {
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionM result = parser.parseExpression();
        if (!parser.checkEOF()) {
            throw parser.error("End of input expected, found: '" + parser.ch + "'");
        }
        return result;
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
                result = new CheckedAdd(result, parseTerm());
            } else if (take('-')) {
                result = new CheckedSubtract(result, parseTerm());
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
                if (take('*')) {
                    result = new CheckedPow(result, parseFactor());
                } else {
                    result = new CheckedMultiply(result, parseFactor());
                }
            } else if (take('/')) {
                if (take('/')) {
                    result = new CheckedLog(result, parseFactor());
                } else {
                    result = new CheckedDivide(result, parseFactor());
                }

            } else {
                return result;
            }
        }
    }

    private ExpressionM parseFactor() {
        skipWS();

        if (take('-')) {
            if (between('0', '9')) {
                return parseNumber("-");
            }
            return new CheckedNegate(parseFactor());
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
        while (!checkEOF() && Character.isWhitespace(ch)) {
            take();
        }
    }
}