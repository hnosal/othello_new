/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.board;

import othello.board.Field;
import othello.board.Field;

/**
 *
 * @author Jan Nosal Andrea Stejskalova
 */
public interface Rules {
    int getSize();
    int numberDisks();
    Field createField(int row, int col);
}
