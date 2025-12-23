package expression.exceptions;

import expression.parser.ExpressionM;
import expression.Add;

public class CheckedAdd extends Add implements CheckMath {
    public CheckedAdd(ExpressionM left, ExpressionM right) {
        super(left, right);
    }
        @Override
        protected int apply(int left, int right) {
            return addcheck(left, right);
        }
    }

