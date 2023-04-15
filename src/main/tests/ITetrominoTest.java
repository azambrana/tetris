import org.junit.jupiter.api.Test;
import views.pieces.Block;
import views.pieces.ITetromino;
import views.pieces.Piece;
import views.models.Point;

import java.awt.*;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;

class ITetrominoTest {

    @Test
    void testCreateInitialState() {
        /*
        0000
        1111
        0000
        0000
        */
        Piece tetromino = ITetromino.create(new Point(0, 0)); // create method is the SUT
        Optional<? extends Block>[][] cells = tetromino.getCells();
        int rows = cells.length;
        int columns = cells[0].length;

        Block red = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.RED);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
                {Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone())},
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()}
        };

        assertArrayEquals(initialState, cells);
        System.out.println(tetromino);
    }

    @Test
    void testCreateStateAfterRotation() {
        /*
        0000
        1111
        0000
        0000
        */
        Piece tetromino = ITetromino.create(new Point(0, 0)); // create method is the SUT
        Optional<? extends Block>[][] cells = tetromino.getCells();
        int rows = cells.length;
        int columns = cells[0].length;
        System.out.println(tetromino);
        Optional<? extends Block>[][] newCells = tetromino.rotateRight();;

        System.out.println(tetromino);

        Block red = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.RED);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()}
        };

        assertArrayEquals(initialState, newCells);

        System.out.println(tetromino);

        newCells = tetromino.rotateRight();;

        System.out.println(tetromino);
        newCells = tetromino.rotateRight();;

        System.out.println(tetromino);
    }

    @Test
    public void testClone() {
        Piece tetromino = ITetromino.create(new Point(0, 0)); // create method is the SUT
        System.out.println(tetromino);
        Piece clonedTetromino = tetromino.clone();

        assertArrayEquals(tetromino.getCells(), clonedTetromino.getCells());

        clonedTetromino.rotateRight();
        System.out.println(tetromino);
        tetromino.rotateRight();
        System.out.println(tetromino);
        System.out.println(clonedTetromino);

        assertArrayEquals(tetromino.getCells(), clonedTetromino.getCells());
    }
}