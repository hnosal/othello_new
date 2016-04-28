/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.board;

/**
 *
 * @author Jan Nosal Andrea Stejskalova
 */
public class Disk {
    
    public boolean barva;
         
    public Disk(boolean isWhite) {
        this.barva = isWhite;
    }
    
    public void turn(){
        barva = !barva;
    }
    
    public boolean isWhite(){
        return barva;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disk other = (Disk) obj;
        if (this.barva != other.barva) {
            return false;
        }
        return true;
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.barva ? 1 : 0);
        return hash;
    }
}
