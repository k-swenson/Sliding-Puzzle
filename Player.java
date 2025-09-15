public class Player {
    private String name;
    private int num_moves;

    public Player(String name) {
        this.name = name;
        this.num_moves = 0;
    }

    public String getName() {
        return name;
    }

    public int getMoves() {
        return num_moves;
    }

    public void incrementMoves() {
        num_moves++;
    }
}
