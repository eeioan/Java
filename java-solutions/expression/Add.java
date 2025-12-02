package expression;


public class Add extends AbstractBinary {
    public Add(ExpressionM left, ExpressionM right) {
        super(left, right);
    }
    @Override
    protected int apply(int left, int right) {
        return left + right;
    }
    @Override
    protected long apply(long left, long right) { return left + right; }
    @Override
    protected String getOperator() {
        return "+";
    }
}