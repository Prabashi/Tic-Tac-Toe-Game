package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


class TicTacToe implements ActionListener {

    private JFrame jFrame;

    private JButton[] btn = new JButton[9]; //button array for 9 slots
    private boolean[] marked = new boolean[9]; //to keep track if a slot is marked
    private int move = 0; // the number of the move; max is 9

    private int comSelection; //index of the computer's selected button
    private boolean isWon;
    private Random rand = new Random();


    TicTacToe() {

        jFrame = new JFrame("Tic Tac Toe");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for (int i = 0; i < 9; i++) {
            btn[i] = new JButton("");
            jFrame.add(btn[i]);
            btn[i].addActionListener(this);
            marked[i] = false;
        }

        jFrame.setLayout(new GridLayout(3, 3));

        jFrame.setSize(300, 300);
        jFrame.setVisible(true);
    }


    private boolean ifPlayerWon(String mark) {
        if (btn[0].getText().equals(mark) && btn[1].getText().equals(mark) && btn[2].getText().equals(mark)) {
            return true;
        } else if (btn[3].getText().equals(mark) && btn[4].getText().equals(mark) && btn[5].getText().equals(mark)) {
            return true;
        } else if (btn[6].getText().equals(mark) && btn[7].getText().equals(mark) && btn[8].getText().equals(mark)) {
            return true;
        } else if (btn[0].getText().equals(mark) && btn[3].getText().equals(mark) && btn[6].getText().equals(mark)) {
            return true;
        } else if (btn[1].getText().equals(mark) && btn[4].getText().equals(mark) && btn[7].getText().equals(mark)) {
            return true;
        } else if (btn[2].getText().equals(mark) && btn[5].getText().equals(mark) && btn[8].getText().equals(mark)) {
            return true;
        } else if (btn[0].getText().equals(mark) && btn[4].getText().equals(mark) && btn[8].getText().equals(mark)) {
            return true;
        } else if (btn[2].getText().equals(mark) && btn[4].getText().equals(mark) && btn[6].getText().equals(mark)) {
            return true;
        } else {
            return false;
        }
    }

    private void resetBoard() {
        move = 0;
        for (int i = 0; i < 9; i++) {
            btn[i].setEnabled(true);
            btn[i].setText("");
            marked[i] = false;
        }
    }

    public void actionPerformed(ActionEvent e) {

        if ((move + 1) % 2 == 1) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == btn[i] && !marked[i]) {
                    move++;
                    btn[i].setText("O");
                    btn[i].setEnabled(false);
                    marked[i] = true;

                    //check if the player won
                    isWon = ifPlayerWon("O");

                    if (isWon) {
                        //pop-up, You won
                        JOptionPane.showMessageDialog(jFrame, "You won!");
                        resetBoard();
                    }
                    else if (move == 9) {
                        //end of the game; draw
                        JOptionPane.showMessageDialog(jFrame, "Draw!");
                        resetBoard();
                    }
                }
            }
        }

        if ((move + 1) % 2 == 0) {

            // computer's turn
            do {
                comSelection = rand.nextInt(9);
            } while (marked[comSelection]);

            btn[comSelection].setText("X");
            btn[comSelection].setEnabled(false);
            marked[comSelection] = true;
            move++;

            //check if the computer won
            isWon = ifPlayerWon("X");

            if (isWon) {
                //pop-up, Computer won
                JOptionPane.showMessageDialog(jFrame, "Computer won!");
                resetBoard();
            }
            else if (move == 9) {
                //end of the game; draw
                JOptionPane.showMessageDialog(jFrame, "Draw!");
                resetBoard();
            }
        }
    }
}


