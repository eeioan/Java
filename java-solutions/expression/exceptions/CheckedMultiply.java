package expression.exceptions;
import expression.Multiply;
import expression.parser.ExpressionM;

public class CheckedMultiply extends Multiply implements CheckMath {
    public CheckedMultiply(ExpressionM left, ExpressionM  right) {
        super(left, right);
    }
    @Override
    protected int apply(int left, int right) {
        return multiplycheck(left, right);
    }
}