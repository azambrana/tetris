import java.util.Optional;

public abstract class Piece {
    private Optional<? extends Block>[][] cells;
    private Point pivot;

    public Piece(Optional<? extends Block>[][] cells, Point pivot) {
        this.cells = cells;
        this.pivot = pivot;
    }

    /**
     * 90 degrees clockwise rotation of the blocks inside the piece
     * @return matrix with the blocks with the new positions
     */
    public Optional<? extends Block>[][] rotateRight() {
        int m = cells.length;
        int n = cells[0].length;
        Optional<? extends Block>[][] result = new Optional[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][m - i - 1] = cells[i][j];
            }
        }
        this.cells = result;

        return result;
    }

    public Optional<? extends Block>[][] getCells() {
        return cells;
    }
}
