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

package com.tactlessfish.connectfour.panels;

import com.tactlessfish.connectfour.ConnectFour;
import com.tactlessfish.connectfour.interfaces.JavaArcade;
import com.tactlessfish.connectfour.shapes.ConnectFourBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class UserPanel extends JPanel implements KeyListener, ActionListener, JavaArcade {
    private static Properties properties = ConnectFour.getProperties();

    private Timer timer; //controls how often we updated the x, y pos of enemies and how often we repaint
    private Timer pointsTimer; //controls how often our points value change
    private ConnectFourBoard connectFourBoard;

    private boolean running = false;
    private int x;
    private int y;
    private int points = 0;

    public UserPanel(int width, int height) {
        // Make board proportional to height/width of panel.
        double boardHeight = height / 1.25;
        double boardWidth = boardHeight * (ConnectFourBoard.getCOLUMNS() / (double) ConnectFourBoard.getROWS());
        connectFourBoard = new ConnectFourBoard(width / 2.0 - boardWidth / 2.0, height / 2.0 - boardHeight / 2.0,
                boardWidth, boardHeight);

        // Status check every 50 milliseconds
        timer = new Timer(50, this);

        addKeyListener(this); //used for key controls

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.decode(properties.getProperty("backgroundColor")));

        setPreferredSize(new Dimension(width, height));
    }

    //draws everything
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        connectFourBoard.drawAll(graphics2D);

        g.setColor(Color.decode(properties.getProperty("paragraphColor")));
        g.setFont(Font.getFont(properties.getProperty("paragraphFont")));
        g.drawString("Points: " + points, 20, getHeight() - 30);

        if (!running) { //shows instructions in the beginning
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
        return running;
    }

    /**
     * This method should start your game, and it should also set a global boolean value so that your
     * isRunning method can return the appropriate value.
     */
    @Override
    public void startGame() {
        running = true;
    }

    /**
     * This method should return the name of your game.
     *
     * @return String containing the game name
     */
    @Override
    public String getGameName() {
        return "Connect Four";
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
