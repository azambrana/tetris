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

        Block red = new Block(ITetromino.WIDTH, ITetromino.HEIGHT, Color.RED);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
                {Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone())},
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()}
        };

        assertArrayEquals(initialState, cells);

        printCells(cells, rows, columns);

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
        printCells(cells, rows, columns);
        Optional<? extends Block>[][] newCells = tetromino.rotateRight();;

        printCells(newCells, rows, columns);

        Block red = new Block(ITetromino.WIDTH, ITetromino.HEIGHT, Color.RED);

        Optional<? extends Block>[][] initialState = new Optional[][]{
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()},
                {Optional.empty(), Optional.empty(), Optional.of(red.clone()), Optional.empty()}
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