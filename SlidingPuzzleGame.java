import java.util.Scanner;

public class SlidingPuzzleGame extends Game {
    private SlidingPuzzleBoard board;
    private boolean playAgain;

    public void start() {
        scanner = new Scanner(System.in);
        playAgain = false;

        System.out.println("Welcome to the Sliding Puzzle Game!");
        System.out.print("What is your name? ");
        String playerName = scanner.nextLine();
        player = new Player(playerName);
        do {
            player.resetMoves();
            boardInput();
            gameTurn();
            System.out.print(String.format("Congrats %s! You solved the sliding puzzle in %d moves! Play again?(y/n): ",
                                            player.getName(), player.getMoves()));
            String input = scanner.nextLine();
            input = input.toLowerCase();
            playAgain = input.equals("y");
        } while (playAgain);

        scanner.close();
    }

    public void gameTurn() {
        while (!board.isSolved()){
            board.display();
            String msg = String.format("%s, which tile do you want to slide to the empty space? ",
                                        player.getName());
            int tile = readInt(scanner, msg);
            if (!board.slideTile(tile)) {
                System.out.println("INVALID TILE, TRY AGAIN");
            } else {
                player.incrementMoves();
            }
        }
    }

    private void boardInput() {
        boolean validBoard = false;
        do {
            int rows = readInt(scanner, "Number of Rows? ");
            int cols = readInt(scanner, "Number of Cols? ");
            try {
                board = new SlidingPuzzleBoard(rows, cols);
                validBoard = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size bounds, try again.");
            }
        } while(!validBoard);
    }

    private static int readInt(Scanner scanner, String msg) {
        while (true) {
            System.out.print(msg);
            String in = scanner.nextLine().trim();
            try {
                return Integer.parseInt(in);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid integer, try again.");
            }
        }
    }
}