package org.cis1200.Minesweeper;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


public class MSBoard extends JPanel {

    private MineSweeperGame game;


    private JLabel status;

    private JPanel flagP;

    // defines our board in terms of graphics
    public MSBoard(JLabel stats, int skill, JPanel flagP) {
        this.flagP = flagP;
        /* border around MS board */
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        game = new MineSweeperGame(skill);
        reset();

        int[] dims = game.getDims();
        status = stats;
        // allows clicks to change the game board
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Point p = e.getPoint();
                    if (30 <= p.x && p.x <= 50 * dims[1] + 30
                            && 30 <= p.y && p.y <= 50 * dims[0] + 30) {
                        game.playTurn(((30 + p.y) / 50) - 1, ((30 + p.x) / 50) - 1);
                    }


                } else {
                    Point p = e.getPoint();
                    if (30 <= p.x && p.x <= 50 * dims[1] + 30
                            && 30 <= p.y && p.y <= 50 * dims[0] + 30) {
                        game.flagLocation(((30 + p.y) / 50) - 1, ((30 + p.x) / 50) - 1);
                    }
                    List<List<Integer>> flags = game.getFlags();
                    String allFlags = "<html>";

                    for (List<Integer> x : flags) {
                        allFlags += Integer.toString(x.get(0)) + ","
                                + Integer.toString(x.get(1)) + "<br />";
                    }
                    allFlags += "</html>";
                    flagP.removeAll();
                    revalidate();
                    flagP.add(new JLabel(allFlags));
                    flagP.repaint();
                }
                updateBoard();

                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void updateBoard() {
        if (game.checkWin()) {
            status.setText("you won!");
        } else if (game.checkLose()) {
            status.setText("you lost :(");
        } else {
            status.setText("Squares Cleared: " + game.getTurn());

        }


    }

    public Piece[][] getBoard() {
        return game.getBoard();
    }

    public MineSweeperGame getGame() {
        return game;
    }

    public int getClicks() {
        return game.getTurn();
    }

    // paints the graphics and all images
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] dims = game.getDims();
        Piece[][] board = game.getBoard();
        TileImage tile;
        for (int row = 0; row < dims[0]; row++) {
            for (int col = 0; col < dims[1]; col++) {
                if (board[row][col].hasClicked()) {
                    if (!board[row][col].isBomb()) {
                        tile = new TileImage(game.getAroundBombs(row, col));
                    } else {
                        tile = new TileImage(0);
                    }
                    g.drawImage(tile.getIMG(), 30 + (col * 50), 30 + row * 50, 50, 50, null);
                } else if (board[row][col].getFlagged()) {
                    tile = new TileImage(9);
                    g.drawImage(tile.getIMG(), 30 + col * 50, 30 + row * 50, 50, 50, null);
                } else {
                    tile = new TileImage(8);
                    g.drawImage(tile.getIMG(), 30 + col * 50, 30 + row * 50, 50, 50, null);
                }
            }
        }
    }


    public void reset() {
        game.reset();
        int[] show = game.findEmpty();
        game.playTurn(show[0], show[1]);
        repaint();
    }


}
