package expression.exceptions;

import expression.parser.ExpressionM;
import expression.Subtract;

public class CheckedSubtract extends Subtract implements CheckMath{
    public CheckedSubtract(ExpressionM left, ExpressionM right) {
        super(left, right);
    }
    @Override
    protected int apply(int left, int right) {
        return subtractcheck(left,right);
    }
}
