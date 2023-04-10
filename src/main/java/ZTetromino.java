import java.awt.*;
import java.util.Optional;

public class ZTetromino extends Piece {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    public ZTetromino(Optional<? extends Block>[][] cells, Point pivot) {
        super(cells, pivot);
    }

    public static Piece create(Point pivot) {
        Block blue = new Block(WIDTH, HEIGHT, Color.BLUE);

        Optional<? extends Block>[][] cells = new Optional[][]{
            {Optional.of(blue.clone()), Optional.of(blue.clone()),  Optional.empty()},
            {Optional.empty(),          Optional.of(blue.clone()),  Optional.of(blue.clone())},
            {Optional.empty(),          Optional.empty(),           Optional.empty()}
        };

        return new ZTetromino(cells, pivot);
    }
}
