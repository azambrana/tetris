package views.pieces;

import views.models.Point;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

public class Block implements Cloneable, Drawable {
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
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
        Block block = new Block(width, height, color);

        if ( this.getPosition() != null) {
            block.setPosition(this.getPosition().clone());
        }

        return block;
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
        return "pieces.Block{" +
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

    public int getRow() {
        return this.position.getRow();
    }

    public int getColumn() {
        return this.position.getColumn();
    }

    @Override
    public void draw(Graphics2D g) {
        Point position = this.getPosition();
        Color color = this.getColor();
        int width = this.getWidth();
        int height = this.getHeight();

        g.setColor(color);
        g.fill3DRect(position.getColumn() * width, position.getRow() * height, width, height, true);
    }
}
