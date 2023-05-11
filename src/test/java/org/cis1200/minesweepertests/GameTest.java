package org.cis1200.minesweepertests;

import org.cis1200.Minesweeper.*;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {

    @Test
    public void testGameState() {
        MineSweeperGame m = new MineSweeperGame(1);
        m.reset();

        m.playTurn(2, 2);

        // ASSERTS that the game works and you can play a turn


        Piece[][] p = m.getBoard();
        assertTrue(p[2][2].hasClicked());
        assertFalse(p[3][3].hasClicked());
    }

    @Test
    public void testClick() {
        Piece[][] board = new Piece[1][1];

        board[0][0] = new Piece(1, 1);

        board[0][0].click();

        assertTrue(board[0][0].hasClicked());
    }

    @Test
    public void testFlag() {
        Piece[][] board = new Piece[1][1];

        board[0][0] = new Piece(1, 1);

        board[0][0].changeFlag();

        assertTrue(board[0][0].getFlagged());
        assertFalse(board[0][0].hasClicked());
    }

    @Test
    public void testGameOver() {
        MineSweeperGame m = new MineSweeperGame(0);
        m.reset();
        int[] dims = m.getDims();
        Piece[][] board = m.getBoard();
        for (int i = 0; i < dims[0]; i++) {
            for (int c = 0; c < dims[1]; c++) {
                if (board[i][c].isBomb()) {
                    m.playTurn(i, c);
                    break;
                }
            }
        }
        assertTrue(m.checkLose());
    }

    @Test
    public void testWin() {
        MineSweeperGame m = new MineSweeperGame(0);
        m.reset();
        int[] dims = m.getDims();
        Piece[][] board = m.getBoard();
        for (int i = 0; i < dims[0]; i++) {
            for (int c = 0; c < dims[1]; c++) {
                if (!board[i][c].isBomb()) {
                    m.playTurn(i, c);

                }
            }
        }
        assertTrue(m.checkWin());
    }

    @Test
    public void testFlags() {
        MineSweeperGame m = new MineSweeperGame(0);
        m.reset();
        m.flagLocation(2, 3);
        m.flagLocation(5, 6);

        List<List<Integer>> x = new LinkedList<>();
        List<Integer> x1 = new LinkedList<Integer>();
        x1.add(2);
        x1.add(3);
        x.add(x1);

        List<Integer> x2 = new LinkedList<Integer>();
        x2.add(5);
        x2.add(6);
        x.add(x2);


        assertEquals(x, m.getFlags());
    }


}
