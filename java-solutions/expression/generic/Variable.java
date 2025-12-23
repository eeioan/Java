package expression.generic;

public class Variable<T> implements GenericExpression<T> {
    private final String name;
    public Variable(String name) { this.name = name; }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (name) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
            default: throw new IllegalArgumentException("Variable not found: " + name);
        }
    }
}