package game;

import java.util.Arrays;

public class Rhomb implements BoardShape {
    private Cell[][] field;

    @Override
    public void initBoard(Cell[][] field) {
        this.field = field;
        int size = field.length * field[0].length - 1;
        int m = (size + 1) / 2;
        int center = m - 1;

        for (Cell[] row : field) {
            Arrays.fill(row, Cell.INVALID);
        }

        for (int i = 0; i < size; i++) {
            field[center][i] = Cell.E;
        }

        for (int i = 0; i < size; i++) {
            field[i][center] = Cell.E;
        }

        for (int offset = 1; offset < m; offset++) {
            int width = m - 1 - offset;
            for (int i = center - width; i <= center + width; i++) {

                if (center - offset >= 0) {
                    field[center - offset][i] = Cell.E;
                }

                if (center + offset < size) {
                    field[center + offset][i] = Cell.E;
                }
            }
        }
    }

    @Override
    public boolean isValidCell(int row, int col) {
        return field[row][col] == Cell.E;
    }
}