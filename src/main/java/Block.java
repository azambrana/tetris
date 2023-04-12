import java.awt.*;
import java.util.Objects;

public class Block implements Cloneable {
    private Point position;
    private int width;
    private int height;
    private Color color;

    public Block(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public Block clone() {
        return new Block(width, height, color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        // && Objects.equals(position, block.position)
        return width == block.width && height == block.height && Objects.equals(color, block.color);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, width, height, color);
    }

    @Override
    public String toString() {
        return "Block{" +
                "position=" + position +
                ", width=" + width +
                ", height=" + height +
                ", color=" + color +
                '}';
    }

    public Color getColor() {
        return this.color;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
