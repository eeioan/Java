package expression.generic;

import expression.exceptions.DivisionByZero;

public class ShortType implements Operation<Short> {
    @Override public Short add(Short x, Short y) { return (short) (x + y); }
    @Override public Short subtract(Short x, Short y) { return (short) (x - y); }
    @Override public Short multiply(Short x, Short y) { return (short) (x * y); }

    @Override
    public Short divide(Short x, Short y) {return (short) (x / y);}

    @Override public Short negate(Short x) { return (short) -x; }
    @Override public Short min(Short x, Short y) { return (short) Math.min(x, y); }
    @Override public Short max(Short x, Short y) { return (short) Math.max(x, y); }
    @Override public Short parseNumber(String n) { return (short) Integer.parseInt(n); }
    @Override public Short valueOf(int n) { return (short) n; }
}