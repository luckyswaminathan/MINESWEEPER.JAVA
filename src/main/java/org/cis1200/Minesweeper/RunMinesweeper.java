package org.cis1200.Minesweeper;

import javax.swing.*;
import java.awt.*;



public class RunMinesweeper implements Runnable {

    // changes from easy to medium
    private int skill = 0;
    private void changeSk(int x) {
        skill = x;
    }



    // runs the game
    public void run() {

        int givenSkill = 0;

        final JFrame frame = new JFrame("Minesweeper!");
        frame.setLocation(300, 300);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("In Game");
        status_panel.add(status);
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton easy = new JButton("easy");
        easy.addActionListener(e -> {
            changeSk(givenSkill);
        });
        control_panel.add(easy);

        final JButton medium = new JButton("medium");
        medium.addActionListener(e -> {
            changeSk(givenSkill);
        });
        control_panel.add(medium, BorderLayout.EAST);

        JPanel flagP = new JPanel(new BorderLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200,500);
            }
        };
        JLabel head = new JLabel("All flags:");
        flagP.add(head);
        MSBoard board = new MSBoard(status, 1, flagP);
        frame.add(board, BorderLayout.CENTER);

        frame.add(flagP, BorderLayout.EAST);



        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);


        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // IOState.writeBoard(board.getBoard(), board.getClicks(), "files/game_storage.csv");
        frame.setVisible(true);



        // Start the game


        board.reset();
        board.repaint();
        frame.repaint();
        flagP.repaint();
    }
}
