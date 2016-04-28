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
import othello.board.Disk;
import othello.board.Field;
import othello.board.Rules;

public class Gui extends JPanel{
    JPanel panel;
    private JPanel boardPanel;
    private JButton[] cell;
    private Board board;
    private Disk white = new Disk(true);
    private Disk black = new Disk(false);
    private static Board start;
    static JLabel score1;
    static JLabel score2;
    private  int userCont = 0;
    static public int playerScore = 2; 
    static public int pcScore = 2;
    private static int center;
    static ArrayList<Board>  arrReversi= new ArrayList<Board>();

    private final int size;
    
    public Gui(int rows,int cols) {
        super(new BorderLayout());    
        setPreferredSize(new Dimension(800,700));
        setLocation(0, 0);
        
        center = rows/2;

        this.size = rows;
        ReversiRules rules = new ReversiRules(this.size);
            //TODOO
        Board hraciP = new Board(rules);
        this.board = hraciP;
        Game game = new Game(board);
        Field[][] field = new Field[rows][cols];
        
        
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800,60));
        
        boardPanel = new JPanel(new GridLayout(rows,cols));
        cell = new JButton[rows*cols];
        int k=0;
        
        for(int row = 0; row < rows; row++) 
        {
            for(int colum=0; colum < cols; colum++) 
            {
                cell[k] = new JButton();
                cell[k].setSize(48, 48);
                cell[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(board.field[row][colum].isEmpty() == true && row == center && colum == center-1 || row == center-1 && colum == center){
                    if(board.field[row][colum].putDisk(white)) {
                        try 
                        {
                            Image img = ImageIO.read(getClass().getResource("/images/disk_white.png"));
                            cell[k].setIcon(new ImageIcon(img));
                        } catch (IOException ex) {}
                        
                    }
                }
                else if(board.field[row][colum].isEmpty() == true && row == center && colum == center || row == center-1 && colum == center-1){
                    if(board.field[row][colum].putDisk(black)){
                        try 
                        {
                            Image img = ImageIO.read(getClass().getResource("/images/disk_black.png"));
                            cell[k].setIcon(new ImageIcon(img));
                        } catch (IOException ex) {}     
                    }                   
                }
            
                else if(row == center && colum == center-2 || row == center+1 && colum == center-1 || 
                        row == center-2 && colum == center || row == center-1 && colum == center+1 )
                {
                    try 
                    {
                        Image img = ImageIO.read(getClass().getResource("/images/field_legal.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ex) {} 
                }
                else{
                    try 
                    {
                        Image img = ImageIO.read(getClass().getResource("/images/field_bg.png"));
                        cell[k].setIcon(new ImageIcon(img));
                    } catch (IOException ex) {}
                }

                cell[k].addActionListener(new MyAction(rows,cols) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(int i = 0;i<rows*cols;i++){
                            if(e.getSource() == cell[i]){
                                System.out.println("button: "+i);  
                                int xCor,yCor;
                                int count = -100,point;
                                int arr[] = new int[3];
                                
                                if(i == 0){
                                    xCor = 0;
                                    yCor = 0;
                                }
                                else{
                                    xCor = i%rows;
                                    yCor = i/cols;
                                }
                            }
                        }
                    }
                });
                
                boardPanel.add(cell[k]);
                k++;
            }
        }
        add(boardPanel, BorderLayout.CENTER);
        
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
        score1.setText(" Player : " + playerScore + "  ");
        score1.setFont(new Font("Serif", Font.BOLD, 16));
        
        score2 = new JLabel();   
        score2.setText(" Computer : " + pcScore + "  ");
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