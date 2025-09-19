import java.util.Scanner;

public class GameController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = GameFactory.createGame("slidingpuzzle", scanner);
        game.start();
        scanner.close();
    }
}