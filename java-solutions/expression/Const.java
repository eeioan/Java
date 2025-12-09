package expression;

import expression.parser.ExpressionM;

import java.util.Objects;

public class Const implements ExpressionM {
    private final int value;
    private final long longValue;
    private final boolean valType;

    public Const(int value) {
        this.value = value;
        this.longValue = value;
        this.valType = false;
    }

    public Const(long value) {
        this.value = (int) value;
        this.longValue = value;

        this.valType = true;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        return longValue;
    }

    @Override
    public String toString() {
        return valType ? String.valueOf(longValue) : String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const that) {
            return Objects.equals(this.value, that.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return valType ? Long.hashCode(longValue) : Integer.hashCode(value);
    }
}

