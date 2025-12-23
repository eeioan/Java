package expression.exceptions;
import expression.Pow;
import expression.parser.ExpressionM;

public class CheckedPow extends Pow implements CheckMath{
    public CheckedPow(ExpressionM left, ExpressionM right) {
        super(left,right);
    }
    @Override
    protected int apply(int left, int right) {
        return powcheck(left,right);
    }
}
