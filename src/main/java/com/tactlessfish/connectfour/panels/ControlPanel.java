/*
 * MIT License
 *
 * Copyright (c) 2020 Fisher Sun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.tactlessfish.connectfour.panels;// Represents a control panel for the arcade

import com.tactlessfish.connectfour.interfaces.JavaArcade;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.*;

public class ControlPanel extends JPanel implements ActionListener {
    private JavaArcade game;
    private GameStats gStats;

    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton instructionsButton;
    private JButton creditsButton;

    // Constructor
    public ControlPanel(JavaArcade t, GameStats g) {
        game = t;
        gStats = g;

        instructionsButton = new JButton("Instructions");
        instructionsButton.addActionListener(this);
        add(instructionsButton);
        add(Box.createHorizontalStrut(80));
        startButton = new JButton("Start");
        startButton.addActionListener(this);

        add(startButton);

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(this);
        add(pauseButton);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        add(stopButton);
        add(Box.createHorizontalStrut(80));
        creditsButton = new JButton("Credits");
        creditsButton.addActionListener(this);
        add(creditsButton);

    }

    // Called when the start button is clicked
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        if (button.equals(startButton)) {
            if (!game.isRunning()) {
                ((JPanel) (game)).requestFocus(); //need to provide the JPanel focus
                game.startGame();
                gStats.update(0);
                gStats.repaint();
            }
        } else if (button.equals(pauseButton)) {
            game.pauseGame();
            startButton.setText("Resume");
            startButton.setEnabled(true);
            repaint();

        } else if (button.equals(stopButton)) {
            game.stopGame();
            gStats.gameOver(game.getPoints());
            gStats.repaint();
            startButton.setEnabled(true);
            startButton.setText("Restart");
            repaint();
        } else if (button.equals(creditsButton)) {
            String credits = game.getCredits();
            JOptionPane.showMessageDialog(this, credits, "Game Credits", JOptionPane.PLAIN_MESSAGE);

        } else if (button.equals(instructionsButton)) {
            String instructions = game.getInstructions();
            JOptionPane.showMessageDialog(this, instructions, "Game Rules", JOptionPane.PLAIN_MESSAGE);

        }
        ((JPanel) (game)).requestFocus();
    }
}
