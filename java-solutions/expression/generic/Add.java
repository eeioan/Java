package expression.generic;

public class Add<T> extends BinaryExpression<T> {
    public Add(GenericExpression<T> left, GenericExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }
    @Override protected T calculate(T a, T b) { return operation.add(a, b); }
}