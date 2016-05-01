/**
 * Othello Game
 * Project for IJA 2015/2016
 * @author Andrea Stejskalova & Jan Nosal
 */

package othello.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    
    private static JPanel panel; // GUI
        
    /* Konstruktor */
    public Main(int flag) {

        super("Othello");
                
        /**
         * GUI
         */
        setLayout(new BorderLayout());
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screen.height;
        int width = screen.width;
        setSize(width/2, height/2);
        setLocationRelativeTo(null);
        
        /* Horni lista GUI s nabidkou */
        JMenuBar menubar = new JMenuBar();
	setJMenuBar(menubar);
        JMenu file = new JMenu("Game");
        menubar.add(file);
        JMenu board = new JMenu("Board");
        menubar.add(board);
        JMenu about = new JMenu("About");
        menubar.add(about);
        
        /* Jednotlive polozky nabidek z horni listy */
        /* Game */
        JMenuItem newGame = new JMenuItem("New");
        file.add(newGame);
        JMenuItem loadGame = new JMenuItem("Load");
        file.add(loadGame);
        JMenuItem saveGame = new JMenuItem("Save");
        file.add(saveGame);
        JMenuItem exitGame = new JMenuItem("Exit");
        file.add(exitGame);
        
        /* Board */
        JMenuItem six = new JMenuItem("6x6");
        board.add(six);
        JMenuItem eight = new JMenuItem("8x8");
        board.add(eight);
        JMenuItem ten = new JMenuItem("10x10");
        board.add(ten);
        JMenuItem twelve = new JMenuItem("12x12");
        board.add(twelve);
        
        /* About */
        JMenuItem help = new JMenuItem("Help");
        about.add(help);
        
        /**
         * Vyber velikosti desky
         */
        switch(flag) {
            case 6:
                panel = new Gui(6,6);
                add(panel, BorderLayout.CENTER);
                setLocationByPlatform(true);
                setSize(500, 346);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setResizable(false);
                setVisible(true);
                break;
            case 10:
                panel = new Gui(10,10);
                add(panel, BorderLayout.CENTER);
                setLocationByPlatform(true);
                setSize(700, 553);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setResizable(false);
                setVisible(true);
                break;
            case 12:
                panel = new Gui(12,12);
                add(panel, BorderLayout.CENTER);
                setLocationByPlatform(true);
                setSize(800, 636);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setResizable(false);
                setVisible(true);
                break;
            default:
                panel = new Gui(8,8);
                add(panel, BorderLayout.CENTER);
                setLocationByPlatform(true);
                setSize(600, 450);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setResizable(false);
                setVisible(true);
                break;
        }
        
        /**
         * Listenery 
         */
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(0);
            }
        });
        
        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(6);
            }
        });
        
        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(0);
            }
        });
        
        ten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(10);
            }
        });
        
        twelve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(12);
            }
        });
        
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(menubar,
                "Seminář Java\nProjekt: Othello\nVypracovali:\nAndrea Stejskalová\nJan Nosál","Informace",JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
    /**
     * MAIN funkce
     * @param args 
     */
    public static void main(String[] args) {        
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main(0);
            }
        });   
    }  
}
