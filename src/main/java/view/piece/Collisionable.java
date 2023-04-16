package view.piece;

import model.CollisionChecker;
import model.Point;

import java.util.List;

public interface Collisionable {
    /**
     * Predicts if the collision is possible when rotating
     * @param boundaries to compare against
     * @return whether the collision is possible or not
     */
    boolean willCollideOnRotate(List<Point> boundaries, CollisionChecker collisionChecker);

    /**
     * Predicts if the collision is possible when moving the piece to the next bottom position
     * @param boundaries to compare against
     * @return whether the collision is possible or not
     */
    boolean willCollideOnDown(List<Point> boundaries, CollisionChecker collisionChecker);

    /**
     * Predicts if the collision is possible when moving the piece to the next left position
     * @param boundaries to compare against
     * @return whether the collision is possible or not
     */
    boolean willCollideOnLeft(List<Point> boundaries, CollisionChecker collisionChecker);

    /**
     * Predicts if the collision is possible when moving the piece to the next right position
     * @param boundaries to compare against
     * @return whether the collision is possible or not
     */
    boolean willCollideOnRight(List<Point> boundaries, CollisionChecker collisionChecker);
}
