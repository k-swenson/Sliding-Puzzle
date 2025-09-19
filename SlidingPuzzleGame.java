import java.util.Scanner;

public class SlidingPuzzleGame extends Game {
    private SlidingPuzzleBoard board;
    private Scanner scanner;
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

    private void gameTurn() {
        while (!board.isSolved()){
            board.display();
            System.out.print(String.format("%s. which tile do you want to slide to the empty space? ",
                                            player.getName()));
            int tile = scanner.nextInt();
            scanner.nextLine();
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
            System.out.print("Number of Rows? ");
            int rows = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Number of Columns? ");
            int cols = scanner.nextInt();
            scanner.nextLine();
            try {
                board = new SlidingPuzzleBoard(rows, cols);
                validBoard = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size bounds, try again.");
            }
        } while(!validBoard);
    }
}