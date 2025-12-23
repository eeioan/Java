package expression;

import expression.parser.ExpressionM;
public class Pow extends AbstractBinary {
    public Pow(ExpressionM left, ExpressionM right) {
        super(left, right);
    }
    @Override
    protected int apply(int left, int right) {
        if (right == 0) return 1;
        if (left == 0) return 0;
        if (left == 1) return 1;

        int result = 1;
        while (right > 0) {
            left *= left;
            right = right/2;
        }
        return result;
    }
    @Override
    public String getOperator(){
        return "**";
    }
    @Override
    protected long apply(long a,long b) {
        int result = 0;
        return result;
    }
}

