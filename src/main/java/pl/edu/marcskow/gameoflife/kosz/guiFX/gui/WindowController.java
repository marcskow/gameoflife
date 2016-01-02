package pl.edu.marcskow.gameoflife.kosz.guiFX.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class WindowController {

    public static void addAndRepaint(Container startingContainer, JComponent componentWhichWillBeOpened){
        startingContainer.removeAll();
        startingContainer.add(componentWhichWillBeOpened);
        startingContainer.repaint();
    }
}
