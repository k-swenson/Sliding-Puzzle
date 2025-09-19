import java.util.Scanner;

public class GameFactory {
    public static Game createGame(String game, Scanner scanner) {
        switch (game) {
            case "slidingpuzzle":
                return new SlidingPuzzleGame(scanner);
                // add more cases later
            default:
                return null;
        }
    }
}