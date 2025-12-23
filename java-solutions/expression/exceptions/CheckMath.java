package expression.exceptions;
public interface CheckMath  {
    default int addcheck(int a, int b) {
        if (b > 0 && a > Integer.MAX_VALUE - b) {
            throw new OverflowException();
        }
        if (b < 0 && a < Integer.MIN_VALUE - b) {
            throw new OverflowException();
        }
        return a + b;
    }

    default int subtractcheck(int a, int b) {
        if (b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException();
        }
        if (b > 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException();
        }
        return a - b;
    }

    default int multiplycheck(int a, int b) {
        if (a == 0 || b == 0) return 0;
        if (a > 0 && b > 0 && a > Integer.MAX_VALUE / b) throw new OverflowException();
        if (a < 0 && b < 0 && a < Integer.MAX_VALUE / b) throw new OverflowException();
        if (a > 0 && b < 0 && b < Integer.MIN_VALUE / a) throw new OverflowException();
        if (a < 0 && b > 0 && a < Integer.MIN_VALUE / b) throw new OverflowException();
        return a * b;
    }

    default int dividecheck(int a, int b) {
        if (b == 0) {
            throw new DivisionByZero();
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException();
        }
        return a / b;
    }

    default int negatecheck(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -a;
    }

    default int powcheck(int a, int b) {
        if (b < 0) throw new ExpressionException("negative power");
        if (a == 0 && b == 0) throw new ExpressionException("0^0 is undefined");
        if (a == 0) return 0;
        if (b == 0) return 1;
        if (a == 1) return 1;

        int result = 1;
        int base = a;
        while (b > 0) {
            if (b % 2 == 1) {
                result = multiplycheck(result, base);
            }
            if (b > 1) {
                base = multiplycheck(base, base);
            }
            b /= 2;
        }
        return result;
    }

    default int logcheck(int a, int b) {
        if (b <= 1) throw new ExpressionException("log base must be > 1");
        if (a <= 0) throw new ExpressionException("log argument must be > 0");

        int result = 0;
        while (a >= b) {
            a /= b;
            result++;
        }
        return result;
    }
}

