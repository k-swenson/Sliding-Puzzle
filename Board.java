import java.util.*;

public class Board {
    private final int shuffle_num;
    private int num_rows;
    private int num_cols;
    private int[][] tiles;
    private int[][] solved_tiles;
    private int empty_row;
    private int empty_col;
    
    public Board(int rows, int cols) {
        this.shuffle_num = SlidingPuzzle.SHUFFLE_NUM;
        this.num_rows = rows;
        this.num_cols = cols;
        this.tiles = new int[rows][cols];
        this.solved_tiles = new int[rows][cols];
        this.empty_row = num_rows-1;
        this.empty_col = num_cols-1;
        initialize();
        shuffle(shuffle_num);
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

    public void shuffle(int num_slides) {
        Random random = new Random();

        // Find all valid adjacent tiles
        for (int i = 0; i < num_slides; i++) {
            List<int[]> adjacent_tiles = new ArrayList<>();

            if (empty_row > 0) { // Up
                int[] adj = {empty_row-1, empty_col};
                adjacent_tiles.add(adj);
            }
            if (empty_row < num_rows - 1) { // Down
                int[] adj = {empty_row+1, empty_col};
                adjacent_tiles.add(adj);
            }
            if (empty_col > 0) { // Left
                int[] adj = {empty_row, empty_col-1};
                adjacent_tiles.add(adj);
            }
            if (empty_col < num_cols - 1) { // Right
                int[] adj = {empty_row, empty_col+1};
                adjacent_tiles.add(adj);
            }

            // Randomly pick adjacent tile
            int[] random_pick = adjacent_tiles.get(random.nextInt(adjacent_tiles.size()));

            // Swap with empty tile
            tiles[empty_row][empty_col] = tiles[random_pick[0]][random_pick[1]];
            tiles[random_pick[0]][random_pick[1]] = 0;

            // Set empty tile coords
            empty_row = random_pick[0];
            empty_col = random_pick[1];
        }
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
        return false;
    }
}
