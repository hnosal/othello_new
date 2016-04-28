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

public class Main extends JFrame{
    
    private static JPanel panel;
    
    public Main(int flag){
        super("Othello");
        setLayout(new BorderLayout());
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screen.height;
        int width = screen.width;
        setSize(width/2, height/2);
        setLocationRelativeTo(null);
        
        /*Hlavní část menu*/
        JMenuBar menubar = new JMenuBar();
	setJMenuBar(menubar);
        JMenu file = new JMenu("Game");
        menubar.add(file);
        JMenu board = new JMenu("Board");
        menubar.add(board);
        JMenu about = new JMenu("About");
        menubar.add(about);
        
        /*item menu*/
        
        JMenuItem newGame = new JMenuItem("New");
        file.add(newGame);
        JMenuItem loadGame = new JMenuItem("Load");
        file.add(loadGame);
        JMenuItem saveGame = new JMenuItem("Save");
        file.add(saveGame);
        JMenuItem exitGame = new JMenuItem("Exit");
        file.add(exitGame);
        
        JMenuItem six = new JMenuItem("6x6");
        board.add(six);
        JMenuItem eight = new JMenuItem("8x8");
        board.add(eight);
        JMenuItem ten = new JMenuItem("10x10");
        board.add(ten);
        JMenuItem twelve = new JMenuItem("12x12");
        board.add(twelve);
        
        JMenuItem help = new JMenuItem("Help");
        about.add(help);
        
        if(flag == 0){
            panel = new Gui(8,8);
            add(panel, BorderLayout.CENTER);
            setLocationByPlatform(true);
            setSize(600, 473);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(true);
            setVisible(true);
        }
        else if(flag == 1){
            panel = new Gui(6,6);
            add(panel, BorderLayout.CENTER);
            setLocationByPlatform(true);
            setSize(500, 373);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(true);
            setVisible(true);
        }
        
        else if(flag == 3){
            panel = new Gui(10,10);
            add(panel, BorderLayout.CENTER);
            setLocationByPlatform(true);
            setSize(700, 573);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(true);
            setVisible(true);
        }
        
        else if(flag == 4){
            panel = new Gui(12,12);
            add(panel, BorderLayout.CENTER);
            setLocationByPlatform(true);
            setSize(800, 673);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(true);
            setVisible(true);
        }
        
        newGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(0);
                this.dispose();
            }
            private void dispose() {}
        });
        
        six.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(1);
                this.dispose();

            }

            private void dispose() {
            }
        });
        
        eight.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(0);
                this.dispose();
            }

            private void dispose() {
            }
        });
        
        ten.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(3);
                this.dispose();
            }

            private void dispose() {
            }
        });
        
        twelve.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Main(4);
                this.dispose();
            }

            private void dispose() {
            }
        });
        
        
        exitGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        help.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(menubar,
                "Seminář Java\nProjekt: Othello\nVypracovali:\nAndrea Stejskalová\nJan Nosál","Informace",JOptionPane.WARNING_MESSAGE);
            }
        });        
    }
    
    public static void main(String[] args) {        

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main(0);
                
            }
        });   
    }
    
}
