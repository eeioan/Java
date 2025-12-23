package expression.exceptions;
import expression.Divide;
import expression.parser.ExpressionM;


public class CheckedDivide extends Divide implements CheckMath {
    public CheckedDivide(ExpressionM left, ExpressionM right) {
        super(left, right);
    }
    @Override
    protected int apply(int left, int right) {
        return dividecheck(left, right);
    }
}
