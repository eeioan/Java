package expression.exceptions;
import expression.Negate;
import expression.parser.ExpressionM;


public class CheckedNegate extends Negate implements CheckMath {
    private final ExpressionM expr;
    public CheckedNegate(ExpressionM expr) {
        super(expr);
        this.expr= expr;
    }
    @Override
    public int evaluate(int x) {
        return negatecheck(expr.evaluate(x));
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return negatecheck(expr.evaluate(x, y, z));
    }
}