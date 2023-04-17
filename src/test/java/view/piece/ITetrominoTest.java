package view.piece;

import org.junit.jupiter.api.Test;
import util.PiecePrinter;
import view.piece.Block;
import view.piece.ITetromino;
import view.piece.Piece;
import model.Point;

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
        // Arrange
        Block red = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.RED);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
                {Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone())},
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()}
        };

        // Act
        Piece tetromino = ITetromino.create(new Point(0, 0)); // create method is the SUT

        // Assert
        Optional<? extends Block>[][] cells = tetromino.getCells();

        assertArrayEquals(initialState, cells);
        PiecePrinter.prettyPrint(tetromino);
    }

    @Test
    void testCreateStateAfterRotation() {
        /*
        0000
        1111
        0000
        0000
        */
        // Arrange
        Piece tetromino = ITetromino.create(new Point(0, 0)); // create method is the SUT

        Block red = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.RED);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()}
        };

        PiecePrinter.prettyPrint(tetromino);

        // Act
        Optional<? extends Block>[][] newCells = tetromino.rotateRight();;

        PiecePrinter.prettyPrint(tetromino);

        // Assert
        assertArrayEquals(initialState, newCells);

        PiecePrinter.prettyPrint(tetromino);
    }

    @Test
    public void testClone() {
        // Arrange
        Piece tetromino = ITetromino.create(new Point(0, 0)); // create method is the SUT
        PiecePrinter.prettyPrint(tetromino);

        // Act
        Piece clonedTetromino = tetromino.clone();
        PiecePrinter.prettyPrint(clonedTetromino);

        // Assert
        assertArrayEquals(tetromino.getCells(), clonedTetromino.getCells());
    }
}