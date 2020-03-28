package com.tactlessfish.connectfour.panels;

import com.tactlessfish.connectfour.interfaces.JavaArcade;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.*;

/**
 * Class representing a control panel for the game.
 */
public class ControlPanel extends JPanel implements ActionListener {
    private JavaArcade game;
    private GameStats gStats;

    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton instructionsButton;
    private JButton creditsButton;

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
