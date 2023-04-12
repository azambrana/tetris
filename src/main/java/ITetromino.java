import java.awt.*;
import java.util.Optional;

public class ITetromino extends Piece {

    public ITetromino(Optional<? extends Block>[][] cells, Point pivot) {
        super(cells, pivot);
    }

    public static Piece create(Point pivot) {
        Block red = new Block(Playfield.BLOCK_WIDTH, Playfield.BLOCK_HEIGHT, Color.RED);

        Optional<? extends Block>[][] cells = new Optional[][]{
            {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
            {Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone()), Optional.of(red.clone())},
            {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()},
            {Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()}
        };

        return new ITetromino(cells, pivot);
    }
}
