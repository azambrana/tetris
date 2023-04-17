package model;

import view.piece.Piece;

import java.util.List;
import java.util.stream.Collectors;

public enum CollisionCheckerImpl implements CollisionChecker<Point, Piece> {
    INSTANCE; // Singleton
    @Override
    public boolean check(List<Point> boundaries, Piece piece) {
        Piece clonedPiece = piece.clone();
        List<Point> pieceBoundaries = clonedPiece.getBlocks().stream().map(block -> block.getPosition()).collect(Collectors.toList());
        return boundaries.stream().anyMatch(point -> pieceBoundaries.stream().anyMatch(p -> p.equals(point)));
    }
}
