package views.pieces;

import views.models.CollisionChecker;
import views.models.Point;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Piece implements Drawable, Movable, Collisionable, Cloneable {
    private final int rows;
    private final int columns;
    private Optional<? extends Block>[][] cells;
    private views.models.Point pivot;
    private views.models.Point[][] positions;

    public Piece(Optional<? extends Block>[][] cells, views.models.Point pivot) {
        this.cells = cells;
        this.pivot = pivot;

        this.rows = cells.length;
        this.columns = cells[0].length;

        positions = new views.models.Point[this.rows][this.columns];
        updatePositions(cells);
    }

    private void updatePositions(Optional<? extends Block>[][] cells) {
        Optional<? extends Block>[][] result = new Optional[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                final views.models.Point point = new views.models.Point(this.pivot.getRow() + row, this.pivot.getColumn() + column);
                positions[row][column] = point;
                cells[row][column].ifPresent(block -> block.setPosition(point));
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
        List<Block> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].ifPresent(result::add);
            }
        }

        return result;
    }

    public void moveUp() {
        this.pivot.addRow(-1);
        updatePositions(cells);
    }

    @Override
    public void moveDown() {
        this.pivot.addRow(1);
        updatePositions(cells);
    }
    @Override
    public void moveLeft(){
        this.pivot.addColumn(-1);
        updatePositions(cells);

    }
    @Override
    public void moveRight(){
        this.pivot.addColumn(1);
        updatePositions(cells);
    }
    @Override
    public boolean willCollideOnRotate(List<views.models.Point> boundaries, CollisionChecker collisionChecker) {
        Piece clonedPiece = this.clone();
        clonedPiece.rotateRight();
        return collisionChecker.check(boundaries, clonedPiece);
    }

    @Override
    public boolean willCollideOnDown(List<views.models.Point> boundaries, CollisionChecker collisionChecker) {
        Piece clonedPiece = this.clone();
        clonedPiece.moveDown();
        return collisionChecker.check(boundaries, clonedPiece);
    }

    @Override
    public boolean willCollideOnLeft(List<views.models.Point> boundaries, CollisionChecker collisionChecker) {
        Piece clonedPiece = this.clone();
        clonedPiece.moveLeft();
        return collisionChecker.check(boundaries, clonedPiece);
    }

    @Override
    public boolean willCollideOnRight(List<views.models.Point> boundaries, CollisionChecker collisionChecker) {
        Piece clonedPiece = this.clone();
        clonedPiece.moveRight();
        return collisionChecker.check(boundaries, clonedPiece);
    }

    @Override
    public void draw(Graphics2D g) {
        this.getBlocks().stream().forEach(block -> block.draw(g));
    }

    @Override
    public String toString() {
        return "pieces.Piece{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", cells=" + cells +
                ", pivot=" + pivot +
                ", positions=" + positions +
                '}';
    }

    @Override
    public Piece clone() {
        Class<? extends Piece> clazz = this.getClass();
        try {
            Constructor<? extends Piece> constructor =
                    clazz.getConstructor(Optional[][].class, views.models.Point.class);
            Optional<? extends Block>[][] clonedCells = new Optional[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    Optional<? extends Block> optionalBlock = cells[row][column];
                    if (optionalBlock.isPresent()) {
                        Block block = optionalBlock.get();
                        clonedCells[row][column] = Optional.ofNullable(block.clone());
                    } else {
                        clonedCells[row][column] = Optional.empty();
                    }
                }
            }

            return constructor.newInstance(clonedCells, new Point(this.pivot.getRow(), this.pivot.getColumn()));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
