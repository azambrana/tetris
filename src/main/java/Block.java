import java.awt.*;
import java.util.Objects;

public class Block implements Cloneable {
    private Point position;
    private int width;
    private int height;
    Color color;

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
        return width == block.width && height == block.height && Objects.equals(position, block.position) && Objects.equals(color, block.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, width, height, color);
    }
}
