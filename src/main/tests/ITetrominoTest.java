import org.junit.jupiter.api.Test;

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

        Block red = new Block(Playfield.BLOCK_WIDTH, Playfield.BLOCK_HEIGHT, Color.RED);

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

        Block red = new Block(Playfield.BLOCK_WIDTH, Playfield.BLOCK_HEIGHT, Color.RED);

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
}