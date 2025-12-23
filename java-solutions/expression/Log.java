package expression;


import expression.parser.ExpressionM;

public class Log extends AbstractBinary {

    public Log(ExpressionM left, ExpressionM right) {
        super(left, right);
    }

    @Override
    protected String getOperator() {
        return "//";
    }

    @Override
    protected int apply(int a, int b) {
        int result = 0;
        while (a >= b) {
            a /= b;
            result++;
        }
        return result;
    }
    @Override
    protected long apply(long a,long b) {
        int result = 0;
        return result;
    }
}