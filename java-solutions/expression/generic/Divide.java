package expression.generic;

public class Divide<T> extends BinaryExpression<T> {
    public Divide(GenericExpression<T> left, GenericExpression<T> right, Operation<T> op) {
        super(left, right, op);
    }
    @Override protected T calculate(T a, T b) { return operation.divide(a, b); }
}