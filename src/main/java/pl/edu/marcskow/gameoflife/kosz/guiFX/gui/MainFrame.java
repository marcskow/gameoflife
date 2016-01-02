package pl.edu.marcskow.gameoflife.kosz.guiFX.gui;

//import pl.edu.marcskow.gameoflife.kosz.guiFX.gui.menu.MainMenu;
import pl.edu.marcskow.gameoflife.kosz.guiFX.gui.menu.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class MainFrame extends JPanel {
    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 720;

    private Container startingContainer;
    private JPanel mainMenuPanel;
    //private MainMenu mainMenu;
    private JPanel titlePanel;

    public MainFrame(Container startingContainer){
        this.startingContainer = startingContainer;
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        titlePanel = new JPanel();
        add(titlePanel,BorderLayout.NORTH);
        titlePanel.setVisible(true);
        //MainMenu mainMenu = new MainMenu(getContentPane());
       // add(mainMenu);
       // mainMenu.setVisible(true);

        mainMenuPanel = new JPanel();

        JButton GameOfLifeButton = new JButton("Game Of Life");
        GameOfLifeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameOfLife();
            }
        });

        JButton LangtonAntButton = new JButton("Langton Ant");
        LangtonAntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 2015-12-31 startNewLangtonAnt
            }
        });

        JButton WireWorldButton = new JButton("Wireworld");
        WireWorldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 2015-12-31 startNewWireWorld
            }
        });

        JButton EllementaryButton = new JButton("Ellementary");
        EllementaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 2015-12-31 startNewEllementaryButton
            }
        });

        JButton QuadLifeButton = new JButton("Quad Life");
        QuadLifeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 2015-12-31 startNewQuadLife
            }
        });

        add(new TitlePanel(), BorderLayout.NORTH);

        mainMenuPanel.add(GameOfLifeButton);
        mainMenuPanel.add(LangtonAntButton);
        mainMenuPanel.add(WireWorldButton);
        mainMenuPanel.add(EllementaryButton);
        mainMenuPanel.add(QuadLifeButton);

        add(mainMenuPanel);

       // pack();

        mainMenuPanel.setVisible(true);
    }

    public void startNewGameOfLife(){
        GameOfLifeGUI gameOfLifeGUI = new GameOfLifeGUI(startingContainer);
        WindowController.addAndRepaint(startingContainer, gameOfLifeGUI);
    }

    public static int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public static int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
}
