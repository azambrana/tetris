package views.models;

import java.util.List;

public interface CollisionChecker<P, T>  {
    /**
     * Checks if piece boundaries collided with the given boundaries
     * @param boundaries boundaries from the playfield
     * @param piece piece to check its boundaries against the given boundaries
     * @return whether the boundaries collide with the piece's boundaries
     */
    boolean check(List<P> boundaries, T piece);
}
