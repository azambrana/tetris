package views.pieces;

import views.models.Point;

import java.awt.*;
import java.util.Optional;

public class ZTetromino extends Piece {
    public ZTetromino(Optional<? extends Block>[][] cells, views.models.Point pivot) {
        super(cells, pivot);
    }

    public static Piece create(Point pivot) {
        Block blue = new Block(Block.BLOCK_WIDTH, Block.BLOCK_HEIGHT, Color.BLUE);

        Optional<? extends Block>[][] cells = new Optional[][]{
            {Optional.of(blue.clone()), Optional.of(blue.clone()),  Optional.empty()},
            {Optional.empty(),          Optional.of(blue.clone()),  Optional.of(blue.clone())},
            {Optional.empty(),          Optional.empty(),           Optional.empty()}
        };

        return new ZTetromino(cells, pivot);
    }
}
