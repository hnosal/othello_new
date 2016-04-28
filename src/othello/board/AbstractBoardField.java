/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.board;

/**
 *
 * @author Jan Nosal Andrea Stejskalova
 * xnosal00@stud.fit.vutbr.cz
 */
public class AbstractBoardField extends java.lang.Object implements Field{
    
    public Field[] around;
    public int row;
    public int col;
    Disk myDisk;  
    
    public AbstractBoardField(int row,int col){
        this.around = new Field[8];            
        this.row = row;
        this.col = col;
        this.myDisk = null;
    }

    @Override
    public void addNextField(Direction dirs, Field field) {
        if(null != dirs)switch (dirs) {
            case D:
                this.around[0] = field;
                break;
            case L:
                this.around[1] = field;
                break;
            case LD:
                this.around[2] = field;
                break;
            case LU:
                this.around[3] = field;
                break;
            case R:
                this.around[4] = field;
                break;
            case RD:
                this.around[5] = field;
                break;
            case RU:
                this.around[6] = field;
                break;
            case U:
                this.around[7] = field;
                break;
            default:
                break;
        }
    }

    @Override
    public Disk getDisk() {
        return myDisk;
    }

    @Override
    public Field nextField(Direction dirs) {
        
        if(dirs == Field.Direction.D){
            return (this.around[0]);
        }
        else if(dirs == Field.Direction.L){
            return (this.around[1]);
        }
        else if(dirs == Field.Direction.LD){
            return (this.around[2]);
        }
        else if(dirs == Field.Direction.LU){
            return (this.around[3]);
        }
        else if(dirs == Field.Direction.R){
            return (this.around[4]);
        }
        else if(dirs == Field.Direction.RD){
            return (this.around[5]);
        }
        else if(dirs == Field.Direction.RU){
            return (this.around[6]);
        }
        else if(dirs == Field.Direction.U){
            return (this.around[7]);
        }
        return null;
    }
   
    @Override
    public boolean putDisk(Disk disk) {
               
        if(this.myDisk == null){
            myDisk = disk;
            return true;
        }
        else 
            return false;   
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof AbstractBoardField) && (this.getClass() == obj.getClass())) {
            final AbstractBoardField other = (AbstractBoardField) obj;
            if ((other.row == this.row) && (other.col == this.col)) {
                return (true);
            }
        }
        return (false);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean canPutDisk(Disk disk) {
        boolean canPut = false;
        if (this.getDisk() == null) {
            for (Direction c : Direction.values()) {
                canPut = canPut || this.canPutDiskDirs(c, this.nextField(c), disk.isWhite());
                if (canPut) {
                    break;
                }
            }
        }
        return (canPut);
    }

    private boolean canPutDiskDirs(Field.Direction dirs, Field field, boolean isWhiteDisk){
        int length = 1;
        while (true) {
            if (field.getDisk() == null) {
                return (false);
            }
            switch (length) {
                case 1:
                    if (field.getDisk().isWhite() == isWhiteDisk) {
                        return (false);
                    }
                    break;
                default:
                    if (field.getDisk().isWhite() == isWhiteDisk) {
                        return (true);
                    }
                    break;
            }
            length++;
            field = field.nextField(dirs);
        }
    }
    @Override
    public String toString(){
        return ("Pole(" + row + "," + col + ") - Kamen:" + myDisk);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
