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
import com.tactlessfish.connectfour.shapes.Pointer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

/**
 * Class representing the panel in which the game is played.
 */
public class UserPanel extends JPanel implements KeyListener, JavaArcade {
    private static Properties properties = ConnectFour.getProperties();
    private GameStats gameStats;

    private ConnectFourBoard connectFourBoard;
    private Pointer pointer;

    private boolean isRunning = true;
    private boolean isCompletedGame;

    private int checkersPlaced = 0;
    private String highScore;

    public UserPanel(int width, int height) {
        addKeyListener(this); //used for key controls

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.decode(properties.getProperty("backgroundColor")));

        setPreferredSize(new Dimension(width, height));
        setSize(width, height);

        connectFourBoard = constructBoard();
        pointer = constructPointer();

        highScore = readHighScore();
        isCompletedGame = false;
    }

    private ConnectFourBoard constructBoard() {
        // Make board proportional to height/width of panel.
        double boardHeight = getHeight() / 1.25;
        double boardWidth = boardHeight * (ConnectFourBoard.getCOLUMNS() / (double) ConnectFourBoard.getROWS());

        return new ConnectFourBoard(getWidth() / 2.0 - boardWidth / 2.0, getHeight() / 2.0 - boardHeight / 2.0,
                boardWidth, boardHeight);
    }

    private Pointer constructPointer() {
        double pointerWidth = connectFourBoard.getCellDiameter();
        double pointerHeight = pointerWidth / 3.0;
        return new Pointer(connectFourBoard.getX(), connectFourBoard.getY() - pointerHeight,
                pointerWidth, pointerHeight);
    }

    private String readHighScore() {
        return properties.getProperty("highScore");
    }

    public void writeHighScore(String newHighScore) {
        properties.setProperty("highScore", newHighScore);

        try (OutputStream outputStream = new FileOutputStream("src/main/resources/config.properties")) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            System.out.println("[WARN] Could not store high score!");
        }
    }

    //draws everything
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        connectFourBoard.drawAll(graphics2D);
        pointer.drawAll(graphics2D);
    }

    /**
     * This method should return true if your game is in a "start" state, and it should return false if
     * your game is in a "paused" state or "stopped" or "unstarted".
     *
     * @return boolean representing if the game is isRunning
     */
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * This method should start your game, and it should also set a global boolean value so that your
     * isRunning method can return the appropriate value.
     */
    @Override
    public void startGame() {
        isRunning = true;
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
     * the game is not running, this value will be returned by isRunning() method.
     */
    @Override
    public void pauseGame() {
        isRunning = false;
    }

    /**
     * This method should return your instructions.
     *
     * @return String containing game instructions
     */
    @Override
    public String getInstructions() {
        return "Move using the arrow keys and drop a checker with space. " +
                "\ntake checkersPlaced dropping checkers until a player wins." +
                "\n\nA player wins when they connect four of their pieces" +
                "\nhorizontally, vertically, or diagonally.";
    }

    /**
     * This method should return the author(s) of the game.
     *
     * @return String containing game author name(s)
     */
    @Override
    public String getCredits() {
        return "Created by Fisher Sun.";
    }

    /**
     * This method should return the highest score played for this game.
     *
     * @return String containing game high score
     */
    @Override
    public String getHighScore() {
        return highScore;
    }

    /**
     * This method should stop the timers, reset the score, and set an isRunning boolean value to false.
     */
    @Override
    public void stopGame() {
        if (isCompletedGame) {
            gameStats.gameOver(checkersPlaced);
        }

        // Reset board, pointer, score
        connectFourBoard = constructBoard();
        pointer = constructPointer();
        checkersPlaced = 0;
        highScore = readHighScore();

        isRunning = false;
        isCompletedGame = false;
    }

    /**
     * This method should return the current player's number of points.
     *
     * @return player's number of points
     */
    @Override
    public int getPoints() {
        return checkersPlaced;
    }

    /**
     * This method provides access to GameStats display for UserPanel to pass information to update score.
     * GameStats is created in ConnectFour; a reference should be passed to UserPanel (main panel) to update points.
     *
     * @param d GameStats to be updated
     */
    @Override
    public void setDisplay(GameStats d) {
        gameStats = d;
        d.update(getPoints());
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Unused
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!isRunning()) {
            return;
        }

        boolean updated = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                updated = takeTurn();
                break;
            case KeyEvent.VK_LEFT:
                updated = pointer.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                updated = pointer.moveRight();
                break;
        }

        if (updated) {
            repaint();
        }
    }

    /**
     * Places a checker on the board and stops the game if the current player wins.
     *
     * @return true if valid placement, false if not
     */
    private boolean takeTurn() {
        if (connectFourBoard.placeChecker(pointer.isP1(), pointer.getCol())) {
            checkersPlaced++;
            setDisplay(gameStats);

            repaint();

            if (connectFourBoard.checkWin()) {
                showWinMessage();
                isCompletedGame = true;
                stopGame();
            }
            pointer.changePlayer();
            return true;
        }
        return false;
    }

    /**
     * Shows a dialog box notifying that a player has won.
     */
    private void showWinMessage() {
        String player;
        if (pointer.isP1()) {
            player = "Player 1";
        } else {
            player = "Player 2";
        }
        JOptionPane.showMessageDialog(this,
                player + " has won." + "\nCheckers placed: " + this.checkersPlaced,
                player + " has won!", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Unused
    }
}
