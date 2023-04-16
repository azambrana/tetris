package view.piece;

import org.junit.jupiter.api.Test;
import util.PiecePrinter;
import view.piece.Block;
import view.piece.Piece;
import model.Point;
import view.piece.ZTetromino;

import java.awt.*;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;

class ZTetrominoTest {

    @Test
    void testCreateStateAfterRotation() {
        /*
        110
        011
        000
        */
        // Arrange
        Piece tetromino = ZTetromino.create(new Point(0, 0)); // create method is the SUT
        PiecePrinter.prettyPrint(tetromino);

        Block blue = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.BLUE);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(),            Optional.of(blue.clone())},
                {Optional.empty(), Optional.of(blue.clone()),   Optional.of(blue.clone())},
                {Optional.empty(), Optional.of(blue.clone()),   Optional.empty()}
        };

        // Act
        Optional<? extends Block>[][] newCells = tetromino.rotateRight();;
        PiecePrinter.prettyPrint(tetromino);

        // Assert
        assertArrayEquals(initialState, newCells);
    }
}