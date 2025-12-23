package expression.generic;

public interface Operation<T> {
    T add(T x, T y);
    T subtract(T x, T y);
    T multiply(T x, T y);
    T divide(T x, T y);
    T negate(T x);

    T min(T x, T y);
    T max(T x, T y);

    T parseNumber(String number);
    T valueOf(int value);
}