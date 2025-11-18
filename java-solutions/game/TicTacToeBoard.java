package game;

public class TicTacToeBoard extends AbstractBoard {

    public TicTacToeBoard(int rows, int cols, int win, BoardShape shape) {
        super(rows, cols, win, shape);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) return Result.LOSE;

        int row = move.getRow(), col = move.getCol();
        field[row][col] = move.getCell();

        if (checkWin(row, col, move.getCell())) return Result.WIN;
        if (checkDraw()) return Result.DRAW;

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }
}