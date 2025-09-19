public class Player {
    private String name;
    private int moves;

    public Player() {
        this("Player");
    }

    public Player(String name) {
        this.name = name;
        this.moves = 0;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public int getMoves() {
        return moves;
    }

    public void incrementMoves() {
        moves++;
    }

    public void resetMoves() {
        moves = 0;
    }
}
