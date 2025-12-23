package expression.generic;

public class Subtract<T> extends BinaryExpression<T> {
    public Subtract(GenericExpression<T> left, GenericExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }
    @Override protected T calculate(T a, T b) { return operation.subtract(a, b); }
}