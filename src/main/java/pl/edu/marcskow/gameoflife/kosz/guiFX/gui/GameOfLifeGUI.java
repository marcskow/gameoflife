package pl.edu.marcskow.gameoflife.kosz.guiFX.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class GameOfLifeGUI extends JComponent {
    private Image image;

    public GameOfLifeGUI(Container c){
        setSize(c.getSize());

        image = new ImageIcon("C:\\Users\\Marcin Skowron\\Pictures\\trawa.png").getImage();


    }

    public void paintComponent(Graphics g)
    {
        if (image == null) return;
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        g.drawImage(image, 0, 0, null);

        for (int i = 0; i * imageWidth <= getWidth(); i++)
            for (int j = 0; j * imageHeight <= getHeight(); j++)
                if (i + j > 0) g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
    }
}
