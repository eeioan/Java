package expression.generic;

public abstract class BinaryExpression<T> implements GenericExpression<T> {
    protected final GenericExpression<T> left;
    protected final GenericExpression<T> right;

    protected final Operation<T> operation;

    public BinaryExpression(GenericExpression<T> left, GenericExpression<T> right, Operation<T> operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
    protected abstract T calculate(T a, T b);

    @Override
    public T evaluate(T x, T y, T z) {
        T leftValue = left.evaluate(x, y, z);
        T rightValue = right.evaluate(x, y, z);
        return calculate(leftValue, rightValue);
    }
}
