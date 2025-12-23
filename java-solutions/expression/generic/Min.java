package expression.generic;

public class Min<T> extends BinaryExpression<T> {
    public Min(GenericExpression<T> left, GenericExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }
    @Override
    protected T calculate(T a, T b) {
        return operation.min(a, b);
    }
}