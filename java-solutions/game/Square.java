package game;

import java.util.Arrays;

public class Square implements BoardShape{
    private Cell[][] field;
    @Override
    public void initBoard(Cell[][] field) {
        this.field = field;
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
    }

    @Override
    public boolean isValidCell(int row, int col){
        return field[row][col] == Cell.E;

    }

}
