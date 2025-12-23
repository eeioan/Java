package expression.generic;

public class DoubleType implements Operation<Double> {
    @Override public Double add(Double x, Double y) { return x + y; }
    @Override public Double subtract(Double x, Double y) { return x - y; }
    @Override public Double multiply(Double x, Double y) { return x * y; }
    @Override public Double divide(Double x, Double y) { return x / y; }
    @Override public Double negate(Double x) { return -x; }

    @Override public Double min(Double x, Double y) { return Math.min(x, y); }
    @Override public Double max(Double x, Double y) { return Math.max(x, y); }
    @Override public Double parseNumber(String n) { return Double.parseDouble(n); }
    @Override public Double valueOf(int n) { return (double) n; }
}