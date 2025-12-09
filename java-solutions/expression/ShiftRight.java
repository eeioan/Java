package expression;
public class ShiftRight extends AbstractBinary {
    public ShiftRight(ExpressionM l, ExpressionM r) { super(l, r); }
    protected int apply(int l, int r) { return l >> r; }
    protected long apply(long l, long r) { return l >> r; }
    protected String getOperator() { return ">>"; }
}