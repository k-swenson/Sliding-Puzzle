import java.util.*;

public class SlidingPuzzle {
    public static final int SHUFFLE_NUM = 1000;
    public static final int MIN_ROWS = 2;
    public static final int MAX_ROWS = 20;
    public static final int MIN_COLS = 2;
    public static final int MAX_COLS = 20;
    private Player player;
    private Board board;
    private Scanner scanner;
    public static void main(String[] args) {
        SlidingPuzzle puzzle = new SlidingPuzzle();
        puzzle.start();
    }

    public void start() {
        int rows;
        int cols;
        do {
            scanner = new Scanner(System.in);
            System.out.print("Number of Rows: ");
            rows = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Number of Columns: ");
            cols = scanner.nextInt();
            scanner.nextLine();
        } while (!validSize(rows, cols));

        //player = new Player();

        board = new Board(rows, cols);

        while (!board.isSolved()){
            board.display();
            System.out.print("Choose which tile to slide: ");
            int tile = scanner.nextInt();
            if (!board.slideTile(tile)) {
                System.out.println("Invalid Tile, Try Again");
            }
        }
        System.out.print("Sliding Puzzle Solved! Play again?(y/n): ");
        String input = scanner.nextLine();
        input = input.toLowerCase();

        scanner.close();
    }

    private boolean validSize(int rows, int cols) {
        return (rows >= MIN_ROWS && rows <= MAX_ROWS && 
                cols >= MIN_COLS && cols <= MAX_COLS);
    }
}