package expression.generic;

public class Max<T> extends BinaryExpression<T> {
    public Max(GenericExpression<T> left, GenericExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }
    @Override
    protected T calculate(T a, T b) {
        return operation.max(a, b);
    }
}