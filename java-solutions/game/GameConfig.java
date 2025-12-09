package game;

public record GameConfig(int rows, int cols, int win, BoardShape shape) {
    public GameConfig(int rows, int cols, int win, BoardShape shape) {
        if (shape instanceof Rhomb) {
            this.rows = 2 * rows - 1;
            this.cols = 2 * cols - 1;
            this.win = win;
            this.shape = shape;

        } else {
            this.rows = rows;
            this.cols = cols;
            this.win = win;
            this.shape = shape;
        }


    }


    @Override
    public String toString() {
        return String.format("Board: %d x %d, Win condition: %d", rows, cols, win);
    }
}