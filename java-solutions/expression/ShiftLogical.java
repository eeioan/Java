package expression;
public class ShiftLogical extends AbstractBinary {
    public ShiftLogical(ExpressionM l, ExpressionM r) { super(l, r); }
    protected int apply(int l, int r) { return l >>> r; }
    protected long apply(long l, long r) { return l >>> r; }
    protected String getOperator() { return ">>>"; }
}