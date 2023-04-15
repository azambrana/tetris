import views.GameBoard;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {
    public static void main(String[] args) {
     SwingUtilities.invokeLater(() -> {
         Tetris tetris = new Tetris();
         GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
         // multi screen devices support
         tetris.setLocation(screenDevices[screenDevices.length>1?1:0].getDefaultConfiguration().getBounds().x, 0);
         tetris.setVisible(true);
     });
    }

    public Tetris() {
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 1080);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(new GameBoard());
    }
}
