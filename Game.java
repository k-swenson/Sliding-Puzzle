import java.util.Scanner;

public abstract class Game {
    protected Player player;
    protected Scanner scanner;

    public abstract void start();
    public abstract void gameTurn();
}