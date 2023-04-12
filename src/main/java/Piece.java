import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Piece {
    private final int rows;
    private final int columns;
    private Optional<? extends Block>[][] cells;
    private Point pivot;
    private Point[][] positions;

    public Piece(Optional<? extends Block>[][] cells, Point pivot) {
        this.cells = cells;
        this.pivot = pivot;

        this.rows = cells.length;
        this.columns = cells[0].length;

        positions = new Point[this.rows][this.columns];
        updatePositions(cells);
    }

    private void updatePositions(Optional<? extends Block>[][] cells) {
        Optional<? extends Block>[][] result = new Optional[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final Point point = new Point(this.pivot.getRow() + i, this.pivot.getColumn() + j);
                positions[i][j] = point;
                cells[i][j].ifPresent(block -> block.setPosition(point));
            }
        }
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

        updatePositions(cells);

        return result;
    }

    public Optional<? extends Block>[][] getCells() {
        return cells;
    }

    /**
     * Returns the blocks of the piece
     * @return blocks of the piece
     */
    public List<Block> getBlocks() {
        List<Block> result = new ArrayList<Block>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].ifPresent(result::add);
            }
        }

        return result;
    }

    public void moveDown() {
        this.pivot.addRow(1);
        updatePositions(cells);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Optional<? extends Block>[][] cells = this.getCells();
        result.append("╔");
        for (int i = 0; i < columns; i++) {
            result.append("═");
        }
        result.append("╗\n");

        for (int i = 0; i < rows; i++) {
            result.append("║");
            for (int j = 0; j < columns; j++) {
                Optional<? extends Block> cell = cells[i][j];
                if (cell.isPresent() ){
                    // print ascii char
                    result.append("░");
                } else {
                    result.append(" ");
                }
            }
            result.append("║\n");
        }

        result.append("╚");
        for (int i = 0; i < columns; i++) {
            result.append("═");
        }
        result.append("╝\n");

        return result.toString();
    }

    public static void print(Piece tetromino) {
        Optional<? extends Block>[][] cells = tetromino.getCells();
        int rows = cells.length;
        int columns = cells[0].length;

        System.out.println();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Optional<? extends Block> cell = cells[i][j];
                System.out.print(cell.isPresent()?"X":"0");
            }
            System.out.println();
        }
    }
}
