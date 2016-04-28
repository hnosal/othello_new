/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.game;

import othello.board.Board;
import othello.board.Disk;
import othello.board.Field;
import othello.board.Field.Direction;

/**
 *
 * @author Jan Nosal Andrea Stejskalova
 */
public class Player {
    public boolean isWhite;
    public String color;
    public int count;
           
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
    
    public void turn(){
        isWhite = !isWhite;
    }
    
    public boolean canPutDisk(Field field){
        return (field.canPutDisk(new Disk(this.isWhite)));
    }
    
    /* public void setUndo(Undo undo)    {
        this.undo = undo;
    }*/
    
    public boolean emptyPool() {
        return this.count == 0;
    }
    
    public void init(Board board){
        this.count -= 2;
        this.count = board.getNumberDisks();
        int center = board.getSize() / 2;
        if (this.isWhite) {
            board.getField(center, center).putDisk(new Disk(true));
            board.getField(center + 1, center + 1).putDisk(new Disk(true));
        } else {
            board.getField(center + 1, center).putDisk(new Disk(false));
            board.getField(center, center + 1).putDisk(new Disk(false));
        }
    }    
    
    public boolean isWhite(){
        return this.isWhite;
    }
    
    public boolean putDisk(Field field){
        boolean canPut = false;
        if (field.getDisk() == null) {
            for (Direction c : Direction.values()) {
                canPut = canPut || this.putDiskDirs(c, field.nextField(c), 1);
            }
            if (canPut) {
                field.putDisk(new Disk(isWhite));
                this.count--;
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
}
