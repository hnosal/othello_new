/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.board;

/**
 *
 *  @author Jan Nosal Andrea Stejskalova
 */
public interface Field {
    public void addNextField(Field.Direction dirs, Field field);
    public Field nextField(Field.Direction dirs);  
    public boolean putDisk(Disk disk);
    public boolean isEmpty();
    public boolean canPutDisk(Disk disk);
    public othello.board.Disk getDisk();
    
    public static enum Direction {
        D,L,LD,LU,R,RD,RU,U;
    }
    
    public static Field.Direction valueOf(java.lang.String name){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    public static Field.Direction[] values()  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

   
