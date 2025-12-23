package expression.generic;

public class Multiply<T> extends BinaryExpression<T> {
    public Multiply(GenericExpression<T> left, GenericExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }
    @Override protected T calculate(T a, T b) { return operation.multiply(a, b); }
}