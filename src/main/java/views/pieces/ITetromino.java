package views.pieces;

import views.models.Point;

import java.awt.*;
import java.util.Optional;

public class ITetromino extends Piece {

    public ITetromino(Optional<? extends Block>[][] cells, views.models.Point pivot) {
        super(cells, pivot);
    }

    public static Piece create(Point pivot) {
        Block red = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.RED);

        Optional<? extends Block>[][] cells = new Optional[][]{
            {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
            {Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone())},
            {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
            {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()}
        };

        return new ITetromino(cells, pivot);
    }
}
