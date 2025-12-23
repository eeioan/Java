package expression.generic;

import expression.parser.BaseParser;
import expression.parser.StringSource;

public class ExpressionParser<T> extends BaseParser {
    private final Operation<T> operation;

    public ExpressionParser(Operation<T> operation) {
        super(new StringSource(""));
        this.operation = operation;
    }

    private ExpressionParser(StringSource source, Operation<T> operation) {
        super(source);
        this.operation = operation;
    }

    public GenericExpression<T> parse(String expression) {
        return new ExpressionParser<>(new StringSource(expression), operation).parseMain();
    }

    private GenericExpression<T> parseMain() {
        GenericExpression<T> result = parseMinMax(); // Начинаем с самого низкого приоритета
        skipWS();
        if (!checkEOF()) {
            throw error("End of input expected, found: '" + ch + "'");
        }
        return result;
    }

    private GenericExpression<T> parseMinMax() {
        GenericExpression<T> result = parseAddSub();
        while (true) {
            skipWS();
            if (take('m')) {
                if (take('i')) {
                    expect('n');
                    result = new Min<>(result, parseAddSub(), operation);
                } else if (take('a')) {
                    expect('x'); // max
                    result = new Max<>(result, parseAddSub(), operation);
                }
            } else {
                return result;
            }
        }
    }

    private GenericExpression<T> parseAddSub() {
        GenericExpression<T> result = parseMulDiv();
        while (true) {
            skipWS();
            if (take('+')) {
                result = new Add<>(result, parseMulDiv(), operation);
            } else if (take('-')) {
                result = new Subtract<>(result, parseMulDiv(), operation);
            } else {
                return result;
            }
        }
    }
    private GenericExpression<T> parseMulDiv() {
        GenericExpression<T> result = parseFactor();
        while (true) {
            skipWS();
            if (take('*')) {
                result = new Multiply<>(result, parseFactor(), operation);
            } else if (take('/')) {
                result = new Divide<>(result, parseFactor(), operation);
            } else {
                return result;
            }
        }
    }
    private GenericExpression<T> parseFactor() {
        skipWS();

        if (take('-')) {
            if (between('0', '9')) {
                return parseNumber("-");
            }
            return new Negate<>(parseFactor(), operation);
        }

        if (take('(')) {
            GenericExpression<T> result = parseMinMax();
            expect(')');
            return result;
        }

        if (between('x', 'z')) {
            String name = String.valueOf(take());
            return new Variable<>(name);
        }

        if (between('0', '9')) {
            return parseNumber("");
        }

        throw error("Unexpected character: '" + ch + "'");
    }

    private GenericExpression<T> parseNumber(String prefix) {
        StringBuilder sb = new StringBuilder(prefix);
        while (between('0', '9')) {
            sb.append(take());
        }
        try {
            return new Const<>(operation.parseNumber(sb.toString()));
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