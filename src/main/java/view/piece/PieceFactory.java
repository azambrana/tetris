package view.piece;

import java.util.Optional;

public interface PieceFactory<T> {
    /**
     * Creates the piece with the desired strategy
     * @return the piece
     */
    Optional<T> createPiece();
}
