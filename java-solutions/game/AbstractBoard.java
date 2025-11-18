package game;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBoard implements Board, Position {
    protected final Cell[][] field;
    protected Cell turn = Cell.X;
    protected final int rows, cols, win;
    protected final BoardShape shape;
    private static final Map<Cell, String> SYM = new HashMap<>();

    static {
        SYM.put(Cell.X, "X");
        SYM.put(Cell.O, "O");
        SYM.put(Cell.E, " ");
        SYM.put(Cell.INVALID, ".");
    }

    public AbstractBoard(int rows, int cols, int win, BoardShape shape) {
        this.rows = rows;
        this.cols = cols;
        this.win = win;
        this.shape = shape;
        this.field = new Cell[rows][cols];
        shape.initBoard(field);
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getWin() {
        return win;
    }

    @Override
    public Position getPosition() {
        return this;
    }
    @Override
    public Cell getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return Cell.INVALID;
        }
        return field[row][col];
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public boolean isValid(Move move) {
        int row = move.getRow(), col = move.getCol();
        return row >= 0 && row < rows && col >= 0 && col < cols &&
                field[row][col] == Cell.E && move.getCell() == turn &&
                shape.isValidCell(row, col);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int numberWidth = String.valueOf(Math.max(rows, cols)).length();

        sb.append(" ".repeat(numberWidth + 1));
        for (int col = 0; col < cols; col++) {
            sb.append(String.format("%" + (numberWidth + 1) + "d", col + 1));
        }
        sb.append("\n");

        sb.append(" ".repeat(numberWidth)).append("+");
        for (int col = 0; col < cols; col++) {
            sb.append("-".repeat(numberWidth + 1));
        }
        sb.append("\n");

        for (int row = 0; row < rows; row++) {
            sb.append(String.format("%" + numberWidth + "d", row + 1)).append("|");
            for (int col = 0; col < cols; col++) {
                String symbol = SYM.get(field[row][col]);
                int padding = numberWidth - symbol.length();
                String leftPad = " ".repeat(padding / 2);
                String rightPad = " ".repeat(padding - padding / 2);
                sb.append(leftPad).append(symbol).append(rightPad);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    protected boolean checkWin(int row, int col, Cell cell) {
        int[][] directions = {{1,0}, {0,1}, {1,1}, {1,-1}};
        for (int[] dir : directions) {
            int count = 1 + countInDirection(row, col, dir[0], dir[1], cell)
                    + countInDirection(row, col, -dir[0], -dir[1], cell);
            if (count >= win) return true;
        }
        return false;
    }

    private int countInDirection(int row, int col, int deltaRow, int deltaCol, Cell cell) {
        int count = 0;
        for (int i = 1; i < win; i++) {
            int newRow = row + deltaRow * i;
            int newCol = col + deltaCol * i;
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols ||
                    field[newRow][newCol] != cell) break;
            count++;
        }
        return count;
    }

    protected boolean checkDraw() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (field[row][col] == Cell.E && shape.isValidCell(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

}
