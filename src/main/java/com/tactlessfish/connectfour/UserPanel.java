/**
 * @(#)UserPanel.java Simplified version of the SpaceInvaders UserPanel to show motion.
 * The two enemy objects move methods are called every 50 ms in response to a timer.
 * repaint() is called in response to that timer.  This call causes paintComponent to
 * be called.  paintComponent redraws the panel.
 * <p>
 * The hero move method is called in response to the mouse events.
 * @author
 * @version 1.00 2016/2/5
 */
package com.tactlessfish.connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class UserPanel extends JPanel implements KeyListener, ActionListener, JavaArcade {
    private javax.swing.Timer timer; //controls how often we updated the x, y pos of enemies and how often we repaint
    private javax.swing.Timer pointsTimer; //controls how often our points value change

    private int checkerDiameter;
    private Checker[][] checkerGrid;

    private boolean start = true;
    private int x, y;
    private int points = 0;

    public UserPanel(int width, int height, Properties properties) {
        Color backColor = Color.black;

        //Make checker proportional to height/width of panel.
        checkerDiameter = getHeight() / 20;

        //Status check every 50 milliseconds
        timer = new javax.swing.Timer(50, this);

        addKeyListener(this);//used for key controls

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(backColor);

        setPreferredSize(new Dimension(width, height));
    }

    //draws everything
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        //TODO draw checkers

        g.setColor(Color.white);
        g.drawString("Points: " + points, 20, getHeight() - 30);

        if (!start) { //shows instructions in the beginning
            g.drawString("Instructions: ... write stuff here", (getWidth() / 2) - 100, getHeight() / 2 + 20);
            g.drawString("(Inactive) Press enter to shoot .", (getWidth() / 2) - 100, getHeight() / 2 + 40);
            g.drawString("You have 3 lives to kill the enemy", (getWidth() / 2) - 100, getHeight() / 2 + 60);
        }
    }

    //<editor-fold desc="JavaArcade">
    /**
     * This method should return true if your game is in a "start" state, and it should return false if
     * your game is in a "paused" state or "stopped" or "unstarted".
     *
     * @return boolean representing if the game is running
     */
    @Override
    public boolean isRunning() {
        return false;
    }

    /**
     * This method should start your game, and it should also set a global boolean value so that your
     * isRunning method can return the appropriate value.
     */
    @Override
    public void startGame() {

    }

    /**
     * This method should return the name of your game.
     *
     * @return String containing the game name
     */
    @Override
    public String getGameName() {
        return null;
    }

    /**
     * This method should stop your timers but save your score. It should set a boolean value to indicate
     * the game is not isRunning, this value will be returned by isRunning() method.
     */
    @Override
    public void pauseGame() {

    }

    /**
     * This method should return your instructions.
     *
     * @return String containing game instructions
     */
    @Override
    public String getInstructions() {
        return null;
    }

    /**
     * This method should return the author(s) of the game.
     *
     * @return String containing game author name(s)
     */
    @Override
    public String getCredits() {
        return null;
    }

    /**
     * This method should return the highest score played for this game.
     *
     * @return String containing game high score
     */
    @Override
    public String getHighScore() {
        return null;
    }

    /**
     * This method should stop the timers, reset the score, and set a isRunning boolean value to false.
     */
    @Override
    public void stopGame() {

    }

    /**
     * This method should return the current player's number of points.
     *
     * @return player's number of points
     */
    @Override
    public int getPoints() {
        return 0;
    }

    /**
     * This method provides access to GameStats display for UserPanel to pass information to update score.
     * GameStats is created in Arcade; a reference should be passed to UserPanel (main panel) to update points.
     *
     * @param d
     */
    @Override
    public void setDisplay(GameStats d) {

    }
    //</editor-fold>

    //<editor-fold desc="ActionListener">
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    //</editor-fold>

    //<editor-fold desc="KeyListener">
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
    //</editor-fold>
}



