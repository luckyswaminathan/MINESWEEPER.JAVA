package org.cis1200.Minesweeper;


public class Piece {
    // instance variables used
    private final int ycord;
    private final int xcord;

    private int bombsAround = 0;

    private final boolean isBomb;
    private boolean isFlagged = false;
    private boolean hasBeenClicked = false;

    // gets the coordinates of a certain piece
    public int[] getCoords() {
        int[] coords = new int[2];
        coords[0] = xcord;
        coords[1] = ycord;
        return coords;
    }

    // defines non-bomb piece
    public Piece(int cordx, int cordy) {
        ycord = cordy;
        xcord = cordx;
        isBomb = false;
    }

    // defines bomb-piece
    public Piece(int cordx, int cordy, boolean bomb) {
        ycord = cordy;
        xcord = cordx;
        isBomb = bomb;
    }

    // returns flag or not
    public boolean getFlagged() {
        return isFlagged;
    }

    // changes flag on piece
    public void changeFlag() {
        isFlagged = !isFlagged;
    }

    // checks if the tile has been clicked
    public boolean hasClicked() {
        return hasBeenClicked;
    }

    // click always sets click to true as you cannot unclick tile
    public void click() {
        hasBeenClicked = true;
    }

    // returns isBomb boolean
    public boolean isBomb() {
        return isBomb;
    }


    public int getBombs() {
        return bombsAround;
    }

    public void setBombs(int x) {
        bombsAround = x;
    }

}
