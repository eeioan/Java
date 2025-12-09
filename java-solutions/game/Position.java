package game;

public interface Position {
    Cell getTurn();

    int getRows();

    int getCols();

    Cell getCell(int row, int col);

    boolean isValid(Move move);
}
