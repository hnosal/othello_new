package othello.game;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import othello.board.Board;
import othello.board.Disk;
import othello.board.Field;
import othello.board.Field.Direction;

public class Player {
    public boolean isWhite;
    public String color;
    public int score = 2;
           
    /* Konstruktor Player */
    public Player(boolean isWhite) {
        this.isWhite = isWhite;
        if(this.isWhite == true){
            this.color = "white";
        }
        else 
            this.color = "black";
    }
    
    @Override
    public String toString(){
        return this.color;
    }
    
    /* Zmena hrace */
    public void turn(){
        isWhite = !isWhite;
    }
    
    /* Zda muze hrac na policku vlozit kamen */
    public boolean canPutDisk(Field field){
        return (field.canPutDisk(new Disk(this.isWhite)));
    }
    
    /* public void setUndo(Undo undo)    {
        this.undo = undo;
    }*/
    
    /* Konec hry */
    public boolean emptyPool() {
        return this.score == 0; // TODO
    }
    
    /* Inicializace hracovych kamenu*/
    public void init(Board board){
        this.score = board.getNumberDisks();
        int center = board.getSize()/2;

        if (this.isWhite) {
            try 
            {
                Image img = ImageIO.read(getClass().getResource("/images/disk_white.png"));
                Gui.cell[center-1][center].setIcon(new ImageIcon(img));
                Gui.cell[center][center-1].setIcon(new ImageIcon(img));
            } catch (IOException ex) {}
                
            board.getField(center-1, center).putDisk(new Disk(this.isWhite));
            board.getField(center, center-1).putDisk(new Disk(this.isWhite));            
        }
        else {
            try 
            {
                Image img = ImageIO.read(getClass().getResource("/images/disk_black.png"));
                Gui.cell[center-1][center-1].setIcon(new ImageIcon(img));
                Gui.cell[center][center].setIcon(new ImageIcon(img));
            } catch (IOException ex) {}

            board.getField(center-1, center-1).putDisk(new Disk(this.isWhite));
            board.getField(center, center).putDisk(new Disk(this.isWhite));     
        }
    }    

    /* Je hrac bily? */
    public boolean isWhite(){
        return this.isWhite;
    }
    
    /* Vlozeni disku */
    public boolean putDisk(Field field){
        boolean canPut = false;
        if (field.getDisk() == null) {
            for (Direction c : Direction.values()) {
                canPut = canPut || this.putDiskDirs(c, field.nextField(c), 1);
            }
            if (canPut) {
                field.putDisk(new Disk(isWhite));
                this.score--;
            }
        }
        return (canPut);
    }
    
    private boolean putDiskDirs(Field.Direction dirs, Field field, int length){
        boolean canPut = false;
        if (field.getDisk() != null) {
            if ((length == 1) && (field.getDisk().isWhite() != this.isWhite)) {
                canPut = this.putDiskDirs(dirs, field.nextField(dirs), length + 1);
            } else if (length > 1) {
                if (field.getDisk().isWhite() == this.isWhite) {
                    return (true);
                }
                canPut = this.putDiskDirs(dirs, field.nextField(dirs), length + 1);
            }
        }
        if (canPut) {
            field.getDisk().turn();
        }
        return (canPut);
    }
    
    /* Zapsani skore */
    public void putScore(int score){
        this.score += score;
    }

    /* Vypsani skore */
    public int getScore(){
        return this.score;
    }
    
    /* Policka, na která lze polozit dalsi kamen */
    public void legalMove(Field field, int row, int col) {
        if(field.canPutDisk(new Disk(this.isWhite))){
            try 
            {
                Image img = ImageIO.read(getClass().getResource("/images/field_legal.png"));
                Gui.cell[row][col].setIcon(new ImageIcon(img));
            } catch (IOException ex) {}
        }
    }
}
