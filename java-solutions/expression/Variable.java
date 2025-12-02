package expression;
import java.util.Objects;

public class Variable implements ExpressionM {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
            default: throw new IllegalArgumentException("unknown variable: " + name);
        }
    }
    @Override
    public long evaluateL(long x, long y, long z) {
        switch (name) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
            default: throw new IllegalArgumentException("unknown variable: " + name);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Variable that) {
            return Objects.equals(name, that.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }
}
