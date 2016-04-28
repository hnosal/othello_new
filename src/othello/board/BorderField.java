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
public class BorderField extends java.lang.Object implements Field {
    public BorderField(){
    }

    @Override
    public Disk getDisk() {
        return null;
    }

    @Override
    public Field nextField(Direction dirs) {
        return null;
    }

    @Override
    public void addNextField(Direction dirs, Field field) {
    }

    @Override
    public boolean putDisk(Disk disk) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean canPutDisk(Disk disk) {
        return false;
    }
}
