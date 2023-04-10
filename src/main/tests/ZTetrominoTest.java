import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

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
        printCells(cells, rows, columns);
        Optional<? extends Block>[][] newCells = tetromino.rotateRight();;

        printCells(newCells, rows, columns);

        Block blue = new Block(ZTetromino.WIDTH, ZTetromino.HEIGHT, Color.BLUE);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(),            Optional.of(blue.clone())},
                {Optional.empty(), Optional.of(blue.clone()),   Optional.of(blue.clone())},
                {Optional.empty(), Optional.of(blue.clone()),   Optional.empty()}
        };

        assertArrayEquals(initialState, newCells);

        printCells(newCells, rows, columns);
    }

    private void printCells(Optional<? extends Block>[][] cells, int rows, int columns) {
        System.out.println();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Optional<? extends Block> cell = cells[i][j];
                if (cell.isPresent()) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }
}