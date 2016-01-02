package pl.edu.marcskow.gameoflife.kosz.guiFX.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.*;


/**
 * Created by Marcin Skowron on 2015-12-31.
 */
public class GuiExample2 {

    private JPanel pnlMain;
    private JDesktopPane desk;

    public GuiExample2(){
        pnlMain = new JPanel(new BorderLayout()){
            @Override public Dimension getPreferredSize(){
                return new Dimension(600,600);
            }
        };
        desk = new JDesktopPane();

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Internal Frame");
        JMenuItem item = new JMenuItem();

        item.setAction(new AbstractAction("Create New") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JInternalFrame iFrame = new JInternalFrame("Created from Menu");
                iFrame.setResizable(true);
                iFrame.setClosable(true);
                iFrame.setIconifiable(true);
                iFrame.setSize(new Dimension(300, 300));
                iFrame.setLocation(0, 0);


                iFrame.setVisible(true);
                desk.add(iFrame);
            }
        });


        menu.add(item);
        bar.add(menu);

        pnlMain.add(bar, BorderLayout.PAGE_START);
        pnlMain.add(desk, BorderLayout.CENTER);
    }

    private JPanel getUI(){
        return pnlMain;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Demo");
                frame.getContentPane().setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new GuiExample2().getUI());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}