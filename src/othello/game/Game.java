package othello.game;

import othello.board.Board;

public class Game {
    private Board board;
    public Player firstPlayer = null;
    public Player secondPlayer = null;
    public boolean currentPlayer = true; //true -> first player -> white, false -> second player -> black
    
    /* Konstruktor Game */
    public Game(Board board){
        this.board = board;
        //this.actionUndo = new Undo();
    }
    
    /* Pridani hrace do hry */
    public boolean addPlayer(Player player) {
        if (this.firstPlayer != null) {
            if (firstPlayer.isWhite() == player.isWhite()) {
                return false;
            }
        } else if (this.secondPlayer != null) {
            if (secondPlayer.isWhite() == player.isWhite()) {
                return false;
            }
        }

        if (this.firstPlayer == null) {
            this.firstPlayer = player;
        } else if (this.secondPlayer == null) {
            this.secondPlayer = player;
        } 
        else {
            return false;
        }
        player.init(this.board); // inicializace pocatecnich kamenu hrace
        //player.setUndo(this.actionUndo);
        return true;
    }

    /* Aktualni hrac */
    public Player currentPlayer() {
        if(this.currentPlayer){
            return this.firstPlayer;
        }
        else 
            return this.secondPlayer;
    }
    
    /* Dalsi hrac na rade*/
    public Player nextPlayer() {
        this.currentPlayer = (this.currentPlayer == false);
        return this.currentPlayer();
    }
    
    /* Ziskani desky */
    public Board getBoard() {
        return this.board;
    }         
}
