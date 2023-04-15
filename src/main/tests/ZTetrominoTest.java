import org.junit.jupiter.api.Test;
import views.pieces.Block;
import views.pieces.Piece;
import views.models.Point;
import views.pieces.ZTetromino;

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
        Piece tetromino = ZTetromino.create(new Point(0, 0)); // create method is the SUT
        Optional<? extends Block>[][] cells = tetromino.getCells();
        int rows = cells.length;
        int columns = cells[0].length;
        System.out.println(tetromino);
        Optional<? extends Block>[][] newCells = tetromino.rotateRight();;

        System.out.println(tetromino);

        Block blue = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.BLUE);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(),            Optional.of(blue.clone())},
                {Optional.empty(), Optional.of(blue.clone()),   Optional.of(blue.clone())},
                {Optional.empty(), Optional.of(blue.clone()),   Optional.empty()}
        };

        assertArrayEquals(initialState, newCells);
        newCells = tetromino.rotateRight();;

        System.out.println(tetromino);

        newCells = tetromino.rotateRight();;

        System.out.println(tetromino);
    }
}