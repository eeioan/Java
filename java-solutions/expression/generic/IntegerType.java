package expression.generic;

import expression.exceptions.DivisionByZero;
import expression.exceptions.OverflowException;

public class IntegerType implements Operation<Integer> {
    private final boolean checkOverflow;

    public IntegerType(boolean checkOverflow) {
        this.checkOverflow = checkOverflow;
    }

    @Override
    public Integer add(Integer x, Integer y) {
        if (checkOverflow) {
            if ((y > 0 && x > Integer.MAX_VALUE - y) || (y < 0 && x < Integer.MIN_VALUE - y)) {
                throw new OverflowException();
            }
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (checkOverflow) {
            if ((y > 0 && x < Integer.MIN_VALUE + y) || (y < 0 && x > Integer.MAX_VALUE + y)) {
                throw new OverflowException();
            }
        }
        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (checkOverflow) {
            if (x > 0) {
                if (y > 0 && x > Integer.MAX_VALUE / y) throw new OverflowException();
                if (y < 0 && y < Integer.MIN_VALUE / x) throw new OverflowException();
            } else if (x < 0) {
                if (y > 0 && x < Integer.MIN_VALUE / y) throw new OverflowException();
                if (y < 0 && x < Integer.MAX_VALUE / y) throw new OverflowException();
            }
        }
        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (checkOverflow && x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        if (checkOverflow && x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

    @Override public Integer min(Integer x, Integer y) { return Math.min(x, y); }
    @Override public Integer max(Integer x, Integer y) { return Math.max(x, y); }
    @Override public Integer parseNumber(String n) { return Integer.parseInt(n); }
    @Override public Integer valueOf(int n) { return n; }
}