package view;

import model.CollisionCheckerImpl;
import view.piece.Drawable;
import view.piece.Piece;
import view.piece.PieceFactory;
import view.piece.PieceFactoryImpl;
import model.Point;
import model.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static view.piece.Block.BLOCK_HEIGHT;
import static view.piece.Block.BLOCK_WIDTH;

public class Playfield extends JPanel implements ActionListener, KeyListener {
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;
    private final int width;
    private final int height;
    private Color borderColor = Color.MAGENTA;
    private Color backgroundColor = Color.GRAY;
    private Grid grid;
    private Drawable currentTetromino;
    private List<Drawable> drawablesShapes;

    private final PieceFactory pieceFactory;
    public Playfield() {
        this.width = BLOCK_WIDTH * COLUMNS;
        this.height = BLOCK_HEIGHT * ROWS;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        this.repaint();
        this.setVisible(true);

        this.setFocusable(true);
        // The KeyListener can be implemented and created in a separated class
        this.addKeyListener(this);
        setFocusable(true);

        pieceFactory = new PieceFactoryImpl();
        this.updateCurrentPiece();

        grid = new Grid(ROWS, COLUMNS);

        invalidateDrawableShapes();

        // The ActionListener can be implemented and created in a separated class
        Timer timer = new Timer(800, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        drawLines((Graphics2D) g);
        drawShapes(drawablesShapes, (Graphics2D) g);
    }

    public void drawShapes(List<Drawable> drawables, Graphics2D g) {
        drawablesShapes.stream().forEach(drawable -> drawable.draw(g));
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
        List<Point> boundaries = grid.getBoundaries();
        boundaries.addAll(this.getBoundaries());
        Piece currentPiece = (Piece)this.currentTetromino;
        moveDownCurrentTetromino(boundaries, currentPiece);
    }

    private void moveDownCurrentTetromino(List<Point> boundaries, Piece currentPiece) {
        if ( !currentPiece.willCollideOnDown(boundaries, CollisionCheckerImpl.INSTANCE) ) {
            currentPiece.moveDown();
        } else {
            dropPiece(currentPiece);
            updateCurrentPiece();
            invalidateDrawableShapes();
        }
        this.repaint();
    }

    private void updateCurrentPiece() {
        this.currentTetromino = (Drawable) pieceFactory.createPiece().get();
    }

    /**
     * Put all the currentPiece's blocks into the the grid (views.Playfield model)
     *
     * @param currentPiece the current piece that cannot be moved to next bottom position
     */
    private void dropPiece(Piece currentPiece) {
        this.grid.addBlocks(currentPiece.getBlocks().stream().map(block -> block.clone()).collect(Collectors.toList()));
    }

    /**
     * Clears and update all the drawable shapes (Blocks, Pieces, and any implementation of pieces.Drawable interface
     */
    private void invalidateDrawableShapes() {
        drawablesShapes = new ArrayList<>();
        drawablesShapes.add(this.currentTetromino);
        drawablesShapes.addAll(grid.getBlocks());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // check if keyevent is the up arrow key
        Piece currentPiece = ((Piece)this.currentTetromino);
        List<Point> boundaries = grid.getBoundaries();
        boundaries.addAll(this.getBoundaries());

        if ( e.getKeyCode() == KeyEvent.VK_UP ) {
            if ( !(currentPiece.willCollideOnRotate(boundaries, CollisionCheckerImpl.INSTANCE)) ) {
                currentPiece.rotateRight();
            }
        } else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            if ( !currentPiece.willCollideOnRight(boundaries, CollisionCheckerImpl.INSTANCE) ) {
                currentPiece.moveRight();
            }
        } else if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
            if ( !currentPiece.willCollideOnLeft(boundaries, CollisionCheckerImpl.INSTANCE) ) {
                currentPiece.moveLeft();
            }
        } else if ( e.getKeyCode() == KeyEvent.VK_DOWN ) {
            moveDownCurrentTetromino(boundaries, currentPiece);
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * This method computes all the positions of the boundaries of the playfield, if the piece of the tetris,
     * i.e. the tetrominoes' blocks, are about to be translated or rotated using the boundaries we will prevent to render
     * outside of the playfield and avoid overlapping among the blocks on the playfield
     * @return all the positions of the boundaries of the playfield, left and right walls and the base of the playfield
     */
    private Collection<? extends Point> getBoundaries() {
        List<Point> points = new ArrayList<Point>();
        for(int row = 0; row < ROWS; row++) {
            points.add(new Point(row, -1)); // left wall
        }

        for(int row = 0; row < ROWS; row++) {
            points.add(new Point(row, COLUMNS)); // right wall
        }

        for(int column = -1; column <= COLUMNS; column++) {
            points.add(new Point(ROWS, column));
        }

        return points;
    }
}
