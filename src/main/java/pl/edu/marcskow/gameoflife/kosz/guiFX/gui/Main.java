package pl.edu.marcskow.gameoflife.kosz.guiFX.gui;

import javax.swing.*;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class Main extends JFrame {
    private static final int DEFAULT2_WIDTH = 1280;
    private static final int DEFAULT2_HEIGHT = 720;

    private MainFrame mainMenu;

    public Main(){
        setSize(DEFAULT2_WIDTH,DEFAULT2_HEIGHT);
        mainMenu = new MainFrame(getContentPane());
        add(mainMenu);
        mainMenu.setVisible(true);
    }

    private void addAndRepaint(JComponent component){
        getContentPane().removeAll();
        add(component);
        repaint();
    }

    public static int getDefaultWidth() {
        return DEFAULT2_WIDTH;
    }

    public static int getDefaultHeight() {
        return DEFAULT2_HEIGHT;
    }
}
