package expression.exceptions;
import expression.Log;
import expression.parser.ExpressionM;

public class CheckedLog extends Log implements CheckMath{
    public CheckedLog(ExpressionM left, ExpressionM right) {
        super(left,right);
    }
    @Override
    protected int apply(int left, int right) {
        return logcheck(left,right);
    }
}
