package expression;

import expression.parser.ExpressionM;

public class ShiftLeft extends AbstractBinary {
    public ShiftLeft(ExpressionM l, ExpressionM r) {
        super(l, r);
    }

    protected int apply(int l, int r) {
        return l << r;
    }

    protected long apply(long l, long r) {
        return l << r;
    }

    protected String getOperator() {
        return "<<";
    }
}