import java.util.*;

public class Board {
    private int num_rows;
    private int num_cols;
    private int[][] tiles;
    private int[][] solved_tiles;
    
    public Board(int rows, int cols) {
        this.num_rows = rows;
        this.num_cols = cols;
        this.tiles = new int[rows][cols];
        this.solved_tiles = new int[rows][cols];
        initialize();
        shuffle();
    }

    private void initialize() {
        int tile = 1;
        for (int r = 0; r < num_rows; r++) {
            for (int c = 0; c < num_cols; c++) {
                solved_tiles[r][c] = tile;
                tiles[r][c] = tile;
                tile++;
            }
        }
        solved_tiles[num_rows - 1][num_cols - 1] = 0;   // empty space
        tiles[num_rows - 1][num_cols - 1] = 0;
    }

    public void shuffle() {
        //TODO!
    }

    public void display() {
        for (int r = 0; r < num_rows; r++) {
            for (int c = 0; c < num_cols; c++) {
                System.out.print(tiles[r][c] + "\t");
            }
            System.out.println();
        }
    }

    public boolean isSolved() {
        for (int r = 0; r < num_rows; r++) {
            for (int c = 0; c < num_cols; c++) {
                if (tiles[r][c] != solved_tiles[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean slideTile(int tile) {
        if (tile < 1 || tile > num_rows * num_cols - 1) {   // Check if tile is valid
            return false;
        }

        for (int r = 0; r < num_rows; r++) {
            for (int c = 0; c < num_cols; c++) {
                if (tiles[r][c] == 0) { // Find the empty space
                    // Check adjacent tiles
                    if (r > 0 && tiles[r - 1][c] == tile) { // Up
                        tiles[r][c] = tile;
                        tiles[r - 1][c] = 0;
                        return true;
                    }
                    if (r < num_rows - 1 && tiles[r + 1][c] == tile) { // Down
                        tiles[r][c] = tile;
                        tiles[r + 1][c] = 0;
                        return true;
                    }
                    if (c > 0 && tiles[r][c - 1] == tile) { // Left
                        tiles[r][c] = tile;
                        tiles[r][c - 1] = 0;
                        return true;
                    }
                    if (c < num_cols - 1 && tiles[r][c + 1] == tile) { // Right
                        tiles[r][c] = tile;
                        tiles[r][c + 1] = 0;
                        return true;
                    }
                    return false; // Tile not adjacent to empty space
                }
            }
        }
        throw new IllegalStateException("No empty space found on the board");
    }

    private Set<Integer> adjacentTiles() {
        Set<Integer> adjacent = new HashSet<>();
        for (int r = 0; r < num_rows; r++) {
            for (int c = 0; c < num_cols; c++) {
                if (tiles[r][c] == 0) { // Find the empty space
                    // Check adjacent tiles
                    if (r > 0) { // Up
                        adjacent.add(tiles[r-1][c]);
                    }
                    if (r < num_rows - 1) { // Down
                        adjacent.add(tiles[r+1][c]);
                    }
                    if (c > 0) { // Left
                        adjacent.add(tiles[r][c-1]);
                    }
                    if (c < num_cols - 1) { // Right
                        adjacent.add(tiles[r][c+1]);
                    }
                }
            }
        }
        return adjacent;
    }
}
