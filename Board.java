public abstract class Board {
    protected int rows;
    protected int cols;
    protected int[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    protected abstract boolean validSize(int rows, int cols);
    public abstract void resetBoard();
    public abstract void shuffle();
    public abstract void display();
}