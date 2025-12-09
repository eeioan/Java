package game;

public record Move(int row, int col, Cell cell) {

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", col=" + col +
                ", cell=" + cell +
                '}';
    }
}
