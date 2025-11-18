package game;

public interface BoardShape {
    boolean isValidCell( int row, int col);
    void initBoard (Cell[][] field);
}
