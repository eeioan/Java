package expression.generic;

public class FloatType implements Operation<Float> {
    @Override public Float add(Float x, Float y) { return x + y; }
    @Override public Float subtract(Float x, Float y) { return x - y; }
    @Override public Float multiply(Float x, Float y) { return x * y; }
    @Override public Float divide(Float x, Float y) { return x / y; }
    @Override public Float negate(Float x) { return -x; }
    @Override public Float min(Float x, Float y) { return Math.min(x, y); }
    @Override public Float max(Float x, Float y) { return Math.max(x, y); }
    @Override public Float parseNumber(String n) { return Float.parseFloat(n); }
    @Override public Float valueOf(int n) { return (float) n; }
}