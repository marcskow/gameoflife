package pl.edu.marcskow.gameoflife.kosz.guiFX.gui.menu;

import pl.edu.marcskow.gameoflife.kosz.guiFX.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class TitlePanel extends JComponent {
    private static final int LETTER_WIDTH = 7;
    private static final int MESSAGE_X = MainFrame.getDefaultWidth() / 2 - (14 * LETTER_WIDTH);
    private static final int MESSAGE_Y = 75;
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 100;

    public void paintComponent(Graphics g)
    {
        String title = "Automaton";

        Font f = new Font("Arial", Font.BOLD, 36);
        g.setFont(f);

        g.drawString(title, MESSAGE_X, MESSAGE_Y);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
