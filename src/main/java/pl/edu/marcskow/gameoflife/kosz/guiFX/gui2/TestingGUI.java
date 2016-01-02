package pl.edu.marcskow.gameoflife.kosz.guiFX.gui2;

import pl.edu.marcskow.gameoflife.kosz.guiFX.gui.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class TestingGUI {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                Main mainFrame = new Main();
                mainFrame.setTitle("Automaton");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setVisible(true);
            }
        });
    }


}