import java.util.*;

public class SlidingPuzzle {
    private Player player;
    private Board board;
    private Scanner scanner;
    public static void main(String[] args) {
        SlidingPuzzle puzzle = new SlidingPuzzle();
        puzzle.start();
    }

    public void start() {
        scanner = new Scanner(System.in);
        System.out.print("Number of Rows: ");
        int rows = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Number of Columns: ");
        int cols = scanner.nextInt();
        scanner.nextLine();
        scanner.close();

        board = new Board(rows, cols);
        board.display();
    }
}