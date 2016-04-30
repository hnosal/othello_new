package othello.board;

public class Board extends java.lang.Object {
    
    public Field[][] field;
    public Rules rules;
        
    /* Konstruktor Board */
    public Board(Rules rules){
        this.field = new Field[rules.getSize() + 2][rules.getSize() + 2];
        this.rules = rules;
        this.genPole();
        this.genOkoli(); 
    }        
       
    private void genPole(){    
        int size = rules.getSize();
        Field border = new BorderField();
        for (int row = 0; row <= size + 1; row++) {
            for (int col = 0; col <= size + 1; col++) {
                if ((row == 0) || (row == size + 1)) {
                    this.field[row][col] = border;
                } else if ((col == 0) || (col == size + 1)) {
                    this.field[row][col] = border;
                } else {
                    this.field[row][col] = rules.createField(row, col);
                }
            }
        }
    }
    private void genOkoli(){ 
        for (int i = 1;i <= rules.getSize(); i++){        
            for (int j = 1; j <= rules.getSize(); j++){
                field[i][j].addNextField(Field.Direction.D, this.field[i+1][j]);
                field[i][j].addNextField(Field.Direction.L, this.field[i][j-1]);
                field[i][j].addNextField(Field.Direction.LD, this.field[i+1][j-1]);
                field[i][j].addNextField(Field.Direction.LU, this.field[i-1][j-1]);
                field[i][j].addNextField(Field.Direction.R, this.field[i][j+1]);
                field[i][j].addNextField(Field.Direction.RD, this.field[i+1][j+1]);
                field[i][j].addNextField(Field.Direction.RU, this.field[i-1][j+1]);
                field[i][j].addNextField(Field.Direction.U, this.field[i-1][j]);
            }
        }
    }
    public int getSize() {
        return rules.getSize();
    }

    public Field getField(int row, int col) {
        if((row>=0 && row<=rules.getSize()+1) || (col>=0 && col<=rules.getSize()+1)){
            return field[row][col];
        }
        else return null;
    }
   
    public Rules getRules(){
        return this.rules;
    }
    
    public int getNumberDisks(){
        return (this.rules.numberDisks());
    }
    
    public int play(int row, int col) {
        if(field[row][col].isEmpty()){
            field[row][col].putDisk(new Disk(true));
        }
        return 0;
    }    
    
}
