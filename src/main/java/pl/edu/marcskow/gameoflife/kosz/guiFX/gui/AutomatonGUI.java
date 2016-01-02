package pl.edu.marcskow.gameoflife.kosz.guiFX.gui;

        import javax.swing.*;
        import java.awt.*;

/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class AutomatonGUI {

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

