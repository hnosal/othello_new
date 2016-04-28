/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.game;

import othello.board.Rules;
import othello.board.Field;

/**
 *
 *  @author Jan Nosal Andrea Stejskalova
 */
public class ReversiRules extends java.lang.Object implements Rules{
    private int disk;
    private final int size;
    
    public ReversiRules(int size) {
        this.size = size;
    }

    @Override
    public int getSize(){
        return this.size;
    }
    
    @Override
    public int numberDisks(){
        int count = this.size*this.size/2;
        return count;
    }
    
    @Override
    public Field createField(int row, int col){
        Field field = new BoardField(row,col);
        return field;
    }
}
