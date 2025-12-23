package expression.generic;

import expression.exceptions.DivisionByZero;
import expression.exceptions.OverflowException;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private static final Map<String, Operation<?>> MODES = Map.of(
            "i", new IntegerType(true),
            "u", new IntegerType(false),
            "d", new DoubleType(),
            "bi", new BigIntegerType(),
            "s", new ShortType(),
            "f", new FloatType()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        Operation<?> operation = MODES.get(mode);
        if (operation == null) {
            throw new IllegalArgumentException("Unknown mode: " + mode);
        }
        return fillTable(operation, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] fillTable(Operation<T> op, String expr, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        GenericExpression<T> parsedExpression = new ExpressionParser<>(op).parse(expr);

        int dimX = x2 - x1 + 1;
        int dimY = y2 - y1 + 1;
        int dimZ = z2 - z1 + 1;

        Object[][][] resultTable = new Object[dimX][dimY][dimZ];

        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                for (int k = 0; k < dimZ; k++) {
                    try {
                        T x = op.valueOf(x1 + i);
                        T y = op.valueOf(y1 + j);
                        T z = op.valueOf(z1 + k);
                        resultTable[i][j][k] = parsedExpression.evaluate(x, y, z);
                    } catch (OverflowException | DivisionByZero | ArithmeticException e) {
                        resultTable[i][j][k] = null;
                    }
                }
            }
        }
        return resultTable;
    }
}