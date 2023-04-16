package view;

import javax.swing.JPanel;
import java.awt.Color;

public class GameBoard extends JPanel {

    public GameBoard() {
        this.setBackground(Color.LIGHT_GRAY);
        Playfield playfield = new Playfield();
        add(playfield);

        // ToDo: add panel to render the next tetrominoes
        // ToDo: add panel to display the scoreboard and statistics
        // ToDo: add more controls (pause, save/load, turn on/off bg music)

        setVisible(true);
    }
}
