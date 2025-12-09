package game;

import java.util.Arrays;

public class Rhomb implements BoardShape {
    private Cell[][] field;

    @Override
    public void initBoard(Cell[][] field) {
        this.field = field;
        int size = field.length;
        int center = size / 2;

        for (Cell[] row : field) {
            Arrays.fill(row, Cell.INVALID);
        }

        for (int i = 0; i < size; i++) {
            field[center][i] = Cell.E;
        }

        for (int i = 0; i < size; i++) {
            field[i][center] = Cell.E;
        }

        for (int row = 0; row < size; row++) {
            int dist = Math.abs(row - center);
            int width = center - dist;

            for (int col = center - width; col <= center + width; col++) {
                field[row][col] = Cell.E;
            }
        }

    }


    @Override
    public boolean isValidCell(int row, int col) {
        return field[row][col] == Cell.E;
    }
}