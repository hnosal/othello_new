/**
 * Othello Game
 * Project for IJA 2015/2016
 * @author Andrea Stejskalova & Jan Nosal
 */

package othello.game;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import othello.board.Board;

public class Gui extends JPanel {
    JPanel panel;
    private JPanel boardPanel; // hraci deska
    static JButton[][] cell; // policka na hraci desce
    static JLabel score1; // skore prvniho (cerneho) hrace
    static JLabel score2; // skore druheho (bileho) hrace
    
    private final Board board; // hraci deska
    private final Game game; // konkretni hra
    static public int scoreOne; // skore/pocet kamenu prvniho (cerneho) hrace
    static public int scoreTwo; // skore/pocet kamenu druheho hrace (pocitac)
    private final int size; // velikost hraci desky
    static ArrayList<Board>  arrReversi= new ArrayList<>(); // seznam vsech hracich desek

    /* Konstruktor */
    public Gui(int rows,int cols) { // vyvolani GUI na zaklade velikosti hraci desky (6x6, 8x8, 10x10, 12x12)
        super(new BorderLayout());    
        setPreferredSize(new Dimension(800,700));
        setLocation(0, 0);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,60));
        boardPanel = new JPanel(new GridLayout(rows,cols)); // hraci deska
        cell = new JButton[rows][cols]; // policka na hraci desce
        
        /* Inicializace promennych a objektu */
        this.size = rows;
        ReversiRules rules = new ReversiRules(this.size);
        this.board = new Board(rules); // vytvoreni desky s hracimi policky
        this.game = new Game(this.board); // vytvoreni nove hry s vytvořenou deskou
        Player one = new Player(true); // vytvoreni bileho hrace
        Player two = new Player(false); // vytvoreni cerneho hrace
        this.scoreOne = one.getScore(); // skore bileho hrace
        this.scoreTwo = two.getScore(); // skore cerneho hrace

        /**
         * Inicializace vsech policek na "prazdna"
         */
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                cell[row][col] = new JButton();
                cell[row][col].setSize(48, 48);
                cell[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                /* Nastaveni pozadi policka */
                try 
                {
                    Image img = ImageIO.read(getClass().getResource("/images/field_bg.png"));
                    cell[row][col].setIcon(new ImageIcon(img));
                } catch (IOException ex) {}
                
                /* Po kliknuti na policko dej se vule bozi :D */
                cell[row][col].addActionListener(new MyAction(rows,cols) {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        for(int row = 0; row < rows; row++){
                            for(int col = 0; col < cols; col++){
                                
                                if(e.getSource() == cell[row][col]){
                                    System.out.println("button: "+row+","+col);
    
                                    /* ------ zkouska - nefunkcni ------ */
                                    if(game.currentPlayer().isWhite){
                                        try 
                                        {
                                            Image img = ImageIO.read(getClass().getResource("/images/disk_white.png"));
                                            cell[row][col].setIcon(new ImageIcon(img));
                                        } catch (IOException ex) {}
                                        game.currentPlayer().turn();
                                        for(int i = 0; i < rows; i++){
                                            for(int j = 0; j < cols; j++){
                                                game.currentPlayer().legalMove(board.field[i][j], i, j);
                                            }
                                        }                                
                                    }
                                    else if(!game.currentPlayer().isWhite){
                                        try 
                                        {
                                            Image img = ImageIO.read(getClass().getResource("/images/disk_black.png"));
                                            cell[row][col].setIcon(new ImageIcon(img));
                                        } catch (IOException ex) {}
                                        game.currentPlayer().turn();
                                        for(int i = 0; i < rows; i++){
                                            for(int j = 0; j < cols; j++){
                                                game.currentPlayer().legalMove(board.field[i][j], i, j);
                                            }
                                        }
                                    }
                                    /* ------------------------------- */
                                }
                            }
                        }
                    }
                });
               
                boardPanel.add(cell[row][col]);
            }
        }
                
        add(boardPanel, BorderLayout.CENTER);

        /**
         * Pridani 2 hracu do hry
         */
        game.addPlayer(one);
        game.addPlayer(two);
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                game.currentPlayer().legalMove(board.field[i][j], i, j);
            }
        }

        /**
         * GUI vypis hracu a jejich skore
         */
        JPanel scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        scorePanel.setPreferredSize(new Dimension(210,800));
        
        JLabel dark = new JLabel();
        try 
        {
            Image img = ImageIO.read(getClass().getResource("/images/disk_black.png"));
            dark.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        JLabel light = new JLabel();
        try 
        {
            Image img = ImageIO.read(getClass().getResource("/images/disk_white.png"));
            light.setIcon(new ImageIcon(img));
        } catch (IOException ex) {}
        
        score1 = new JLabel();
        score1.setText(" Player : " + this.scoreOne + "  ");
        score1.setFont(new Font("Serif", Font.BOLD, 16));
        
        score2 = new JLabel();   
        score2.setText(" Computer : " + this.scoreTwo + "  ");
        score2.setFont(new Font("Serif", Font.BOLD, 16));        
               
        c.gridx = 0;
        c.gridy = 1;
        scorePanel.add(dark, c);  
        c.gridx = 1;
        c.gridy = 1;
        scorePanel.add(score1,c);
        
        c.gridx = 0;
        c.gridy = 2;
        scorePanel.add(light, c);  
        c.gridx = 1;
        c.gridy = 2;
        scorePanel.add(score2,c);
              
        add(scorePanel, BorderLayout.EAST);
        
        scorePanel.add(light);
        scorePanel.add(score2);
    }
    
    private static abstract class MyAction implements ActionListener {

        public MyAction(int rows, int cols) {
        }
    }
}