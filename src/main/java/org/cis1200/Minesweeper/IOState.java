package org.cis1200.Minesweeper;

import java.io.*;

public class IOState {
    // checks if it is valid to read this board from I/O
    // note these are static because it stores pre-runtime
    //
    public static boolean checkValidity(Piece[][] newBoard) {
        if (!(newBoard.length == 8 && newBoard[0].length == 8) &&
                !(newBoard.length == 16 && newBoard[0].length == 16)) {
            return false;
        }
        for (int i = 0; i < newBoard.length; i++) {
            for (int c = 0; c < newBoard[0].length; c++) {
                if (newBoard[i][c] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    // stores the file with the number of bombs near a location - reading goes row by row
    // if it is unclicked, stores a 9, if it is flagged, stores 8,
    // if clicked number - 1 through 7, if 0 - stores 0
    // for bomb stores b

    // first want the number of clicks(simply for the GUI)

    // am going to split line by line
    // CAN ASSUME BOARD ISVALID
    public static void writeBoard(Piece[][] newBoard, int clicks, String file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            int skill = (newBoard.length / 8) - 1;
            bw.write(Integer.toString(skill));
            bw.newLine();
            bw.write(Integer.toString(clicks));
            bw.newLine();
            for (int row = 0; row < newBoard.length; row++) {
                for (int col = 0; col < newBoard[0].length; col++) {
                    if (!newBoard[row][col].hasClicked()) {
                        if (newBoard[row][col].isBomb()) {
                            if (newBoard[row][col].getFlagged()) {
                                bw.write("U,b,t");
                                bw.write(" ");
                            } else {
                                bw.write("U,b,f");
                                bw.write(" ");
                            }
                        } else {
                            String bA = Integer.toString(newBoard[row][col].getBombs());
                            if (newBoard[row][col].getFlagged()) {
                                bw.write("U," + bA + ",t");
                                bw.write(" ");
                            } else {
                                bw.write("U," + bA + "f");
                                bw.write(" ");
                            }

                        }
                    } else {
                        String bA = Integer.toString(newBoard[row][col].getBombs());
                        bw.write("C," + bA + ",f");
                        bw.write(" ");
                    }
//
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    public static MineSweeperGame loadBoard(String file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            boolean lost = false;
            boolean won = false;
            int rcount = 0;
            int colNum;
            Piece[][] board;
            int skill = Integer.valueOf(br.readLine());
            if (skill == 0) {
                colNum = 8;
                board = new Piece[8][8];
            } else {
                colNum = 16;
                board = new Piece[16][16];
            }
            int clicks = Integer.valueOf(br.readLine());
            int bombs = 0;
            String x;
            while ((x = br.readLine()) != null) {
                String[] split = x.split(" ");
                for (int i = 0; i < colNum; i++) {
                    String[] secondSplit = split[i].split(",");

                    String clicked = secondSplit[0];
                    String bS = secondSplit[1];
                    String flagged = secondSplit[2];
                    if (bS.equals("b")) {
                        board[rcount][i] = new Piece(rcount, i, true);
                        bombs++;
                        if (clicked.equals("C")) {
                            board[rcount][i].click();
                        } else if (flagged.equals("t")) {
                            board[rcount][i].changeFlag();
                        }
                    } else {
                        board[rcount][i] = new Piece(rcount, i);
                        int bA = Integer.valueOf(bS);
                        board[rcount][i].setBombs(bA);
                        if (clicked.equals("C")) {
                            board[rcount][i].click();
                        } else if (flagged.equals("t")) {
                            board[rcount][i].changeFlag();
                        }
                    }


                }
                rcount++;
            }
            br.close();
            fr.close();
            if (clicks == (colNum * colNum - bombs)) {
                won = true;
            }
            if (checkValidity(board)) {
                MineSweeperGame m = new MineSweeperGame(board);
                m.setTurn(clicks);
                m.setLose(lost);
                m.setWin(won);
                return m;
            } else {
                throw new RuntimeException();
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }


    }


}
