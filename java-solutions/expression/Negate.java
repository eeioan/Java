package expression;
public class Negate implements ExpressionM {
    private final ExpressionM expr;
    public Negate(ExpressionM expr) { this.expr = expr; }
    public int evaluate(int x) { return -expr.evaluate(x); }
    public int evaluate(int x, int y, int z) { return -expr.evaluate(x, y, z); }
    public long evaluateL(long x, long y, long z) { return -expr.evaluateL(x, y, z); }
    public String toString() { return "-(" + expr + ")"; }
}