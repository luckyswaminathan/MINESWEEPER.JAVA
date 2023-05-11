package org.cis1200.Minesweeper;
import java.util.*;

public class MineSweeperGame {
    // Our board that we are going to use to model the game on
    Piece[][] board;
    // the number of bombs in the board(used for the different game types)
    private int numBomb;
    // the total number of squares in the board- used for the generation of a random board.
    private int size;
    // the probability of a certain square being a bomb - used for random board generation
    private Double pr;
    // number of clicks - used to model number of turns - starts at 1 because we open with 1
    private int clickNum = 1;
    // whether game is lost or not
    private boolean lost = false;

    private boolean won = false;

    private List<List<Integer>> flags = new LinkedList<>();


    // defines minesweeper game for two levels: medium and hard
    public MineSweeperGame(int skill) {
        if (skill == 0) {
            board = new Piece[8][8];
            numBomb = 10;
            size = 64;
            pr = 10.0 / 64.0;
        } else {
            board = new Piece[16][16];
            numBomb = 40;
            size = 256;
            pr = 40.0 / 256.0;
        }

        reset();

    }

    public MineSweeperGame(Piece[][] readBoard) {
        board = readBoard;
        int rowL = board.length;
        if (rowL == 8) {
            numBomb = 10;
            size = 64;
            pr = 10.0 / 64.0;

        } else {
            numBomb = 40;
            size = 256;
        }
        pr = 40.0 / 256.0;
    }

    public MineSweeperGame(String file) {
        MineSweeperGame x = IOState.loadBoard(file);
        board = x.getBoard();
        clickNum = x.getTurn();
        if (board.length == 8) {
            numBomb = 10;
        } else {
            numBomb = 40;
        }
        lost = x.lost;
        won = x.checkWin();
    }


    // resets the board with a new random board
    public void reset() {
        int count = 0;
        int bCount = 0;
        int[] dims = getDims();
        while (count < size) {
            int col = count % dims[0];
            int row = (count - col) / dims[1];
            Double x = Math.random();
            if (x < pr) {
                if (bCount < numBomb) {
                    board[row][col] = new Piece(row, col, true);
                    bCount++;
                } else {
                    break;
                }
            } else {
                board[row][col] = new Piece(row, col);
            }
            count++;

        }
        if (bCount < numBomb) {
            reset();
        }
        lost = false;
        clickNum = 0;
    }

    // gets bombs around a certain location
    public int getAroundBombs(int row, int col) {

        int count = 0;

        for (int i = row - 1; i < row + 2; i++) {
            for (int k = col - 1; k < col + 2; k++) {
                if (i >= 0 && i < board.length && k >= 0 && k < board[0].length) {
                    if (board[i][k].isBomb()) {
                        count++;
                    }
                }
            }
        }
        board[row][col].setBombs(count);
        return count;
    }

    // flags a location (uses piece changeFlag method)
    public void flagLocation(int row, int col) {
        board[row][col].changeFlag();
        boolean k = board[row][col].getFlagged();
        List<Integer> dimsF = new LinkedList<Integer>();
        dimsF.add(row);
        dimsF.add(col);
        if (k) {
            flags.add(dimsF);
        } else {
            flags.remove(dimsF);
        }
    }

    // plays turn and clicks a space (if it is blank also clicks all spaces around it)
    public boolean playTurn(int row, int col) {
        int[] dims = getDims();
        if (lost || won) {
            return false;
        } else if (board[row][col].hasClicked()) {
            return false;
        } else if (board[row][col].isBomb()) {
            board[row][col].click();
            lost = true;
            return true;
        } else if (getAroundBombs(row, col) == 0) {

            for (int i = row - 1; i < row + 2; i++) {
                for (int c = col - 1; c < col + 2; c++) {
                    if (0 <= c && c < dims[1] && 0 <= i && i < dims[0]) {
                        if (!board[i][c].hasClicked()) {
                            board[i][c].click();
                            clickNum++;
                        }
                    }
                }
            }
            return true;
        } else {
            board[row][col].click();
            clickNum++;
            return true;
        }

    }

    // checks if win
    public boolean checkWin() {
        if (clickNum + numBomb == size) {
            return true;
        }
        return false;
    }

    // checks if lost
    public boolean checkLose() {
        return lost;
    }

    // gets number of turns
    public int getTurn() {
        return clickNum;
    }

    // gets dimensions of board for references outside/inside class
    public int[] getDims() {
        int[] dims = new int[2];
        dims[0] = board.length;
        dims[1] = board[0].length;
        return dims;
    }

    // gets the board we are using
    public Piece[][] getBoard() {
        return board;
    }

    // finds a non-Bomb tile to click on to begin to help the user start the game
    public int[] findEmpty() {
        int[] dims = new int[2];

        int row = (int) (Math.random() * 8.0);
        int col = (int) (Math.random() * 8.0);

        if (board[row][col].isBomb()) {
            findEmpty();
        } else {
            dims[0] = row;
            dims[1] = col;
        }
        return dims;
    }

    public void setBoard(Piece[][] b) {
        board = b;
    }

    public void setTurn(int x) {
        clickNum = x;
    }

    public void setLose(boolean b) {
        lost = b;
    }

    public void setWin(boolean b) {
        won = b;
    }

    public List<List<Integer>> getFlags() {
        return flags;
    }
}
