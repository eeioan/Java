package game;

public class TicTacToeBoard extends AbstractBoard {

    public TicTacToeBoard(int rows, int cols, int win, BoardShape shape) {
        super(rows, cols, win, shape);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) return Result.LOSE;

        int row = move.row(), col = move.col();
        field[row][col] = move.cell();

        if (checkWin(row, col, move.cell())) return Result.WIN;
        if (checkDraw()) return Result.DRAW;

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }
}