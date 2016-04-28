/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.game;

import othello.board.Board;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 *  @author Jan Nosal Andrea Stejskalova
 */
public class Game {
    private Board board;
    public Player firstPlayer = null;
    public Player secondPlayer = null;
    public boolean currentPlayer = true; //first player
    
    public Game(Board board){
        this.board = board;
        //this.actionUndo = new Undo();
    }
    
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
        player.init(this.board);
        //player.setUndo(this.actionUndo);
        return true;
    }

    public Player currentPlayer() {
        if(this.currentPlayer){
            return this.firstPlayer;
        }
        else 
            return this.secondPlayer;
    }
    
    public Player nextPlayer() {
        this.currentPlayer = (this.currentPlayer == false);
        return this.currentPlayer();
    }
    
    public Board getBoard() {
        return this.board;
    }         
}
