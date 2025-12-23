package expression.generic;

public class Negate<T> implements GenericExpression<T> {
    private final GenericExpression<T> expression;
    private final Operation<T> operation;

    public Negate(GenericExpression<T> expression, Operation<T> operation) {
        this.expression = expression;
        this.operation = operation;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return operation.negate(expression.evaluate(x, y, z));
    }
}