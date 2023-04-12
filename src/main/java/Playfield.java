import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Playfield extends JPanel implements ActionListener {
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    private final int width;
    private final int height;
    private int rows = 20;
    private int columns = 10;
    private Color borderColor = Color.MAGENTA;
    private Color backgroundColor = Color.GRAY;
    private Grid grid;
    private Piece currentTetromino;

    public Playfield() {
        this.width = BLOCK_WIDTH * columns;
        this.height = BLOCK_HEIGHT * rows;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        this.repaint();
        this.setVisible(true);
        grid = new Grid(rows, columns);

        // ToDo: move logic to another class to handle current and next tetrominoes
        this.currentTetromino = ZTetromino.create(new Point(-2, columns/2-2));
        // this.currentTetromino = ITetromino.create(new Point(-1, columns/2-2));

        Timer timer = new Timer(800, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        drawLines((Graphics2D) g);
        // ToDo: draw blocks
        // ToDo: draw tetrominoes and add animations
        drawCurrentTetromino((Graphics2D) g);
        drawBlocks(grid.getBlocks(), (Graphics2D) g);

    }

    private void drawCurrentTetromino(Graphics2D g) {
        List<Block> blocks = this.currentTetromino.getBlocks();
        drawBlocks(blocks, g);
    }

    private void drawBlocks(List<Block> blocks, Graphics2D g) {
        for (Block block : blocks) {
            System.out.println(block);
            drawBlock(block, g);
        }
        System.out.println();
    }

    private void drawBlock(Block block, Graphics2D g) {
        Point position = block.getPosition();
        Color color = block.getColor();
        int width = block.getWidth();
        int height = block.getHeight();

        g.setColor(color);
        g.fill3DRect(position.getColumn() * width, position.getRow() * height, width, height, true);
    }

    private void drawLines(Graphics2D g) {
        Graphics2D g2d = g;
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < width; i += BLOCK_WIDTH) {
            g2d.drawLine(i, 0, i, height);
        }
        for (int i = 0; i < height; i += BLOCK_HEIGHT) {
            g2d.drawLine(0, i, width, i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.currentTetromino.moveDown();
        this.repaint();
    }
}
