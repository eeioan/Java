package expression.exceptions;

public class DivisionByZero extends ExpressionException {
    public DivisionByZero() {
        super("division by zero");
    }
}