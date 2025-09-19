public class Player {
    private String name;
    private int moves;

    public Player() {
        this("Player");
    }

    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
        this.name = name;
        this.moves = 0;
    }

    public String getName() {
        return name;
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
