package expression;

import java.util.Objects;
public abstract class AbstractBinary implements ExpressionM {
    private final ExpressionM left;
    private final ExpressionM right;

    public AbstractBinary (ExpressionM left, ExpressionM right){
        this.left = left;
        this.right = right;
    }
    protected abstract int apply(int left, int right);
    protected abstract long apply(long left, long right);
    protected abstract String getOperator();

    @Override
    public int evaluate(int x){
        return apply(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z){
        return apply(left.evaluate(x,y,z), right.evaluate(x,y,z));
    }

    @Override
    public long evaluateL(long x, long y, long z) {
        return apply(left.evaluateL(x, y, z), right.evaluateL(x, y, z));
    }
    @Override
    public String toString(){
        return "(" + left.toString() + " " + getOperator() + " " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractBinary that = (AbstractBinary) obj;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 7 * result + right.hashCode();
        result = 77 * result + getClass().hashCode();
        return result;
    }
}

