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

package com.tactlessfish.connectfour.interfaces;

import com.tactlessfish.connectfour.panels.GameStats;

/**
 * All games must implement this interface to be compatible with the Java arcade.
 *
 * @author A. DiBenedetto
 * @author F. Sun
 * @version 1.01 2020-03-06
 */
public interface JavaArcade {
    /**
     * This method should return true if your game is in a "start" state, and it should return false if
     * your game is in a "paused" state or "stopped" or "unstarted".
     *
     * @return boolean representing if the game is running
     */
    public boolean isRunning();

    /**
     * This method should start your game, and it should also set a global boolean value so that your
     * isRunning method can return the appropriate value.
     */
    public void startGame();

    /**
     * This method should return the name of your game.
     *
     * @return String containing the game name
     */
    public String getGameName();

    /**
     * This method should stop your timers but save your score. It should set a boolean value to indicate
     * the game is not isRunning, this value will be returned by isRunning() method.
     */
    public void pauseGame();

    /**
     * This method should return your instructions.
     *
     * @return String containing game instructions
     */
    public String getInstructions();

    /**
     * This method should return the author(s) of the game.
     *
     * @return String containing game author name(s)
     */
    public String getCredits();

    /**
     * This method should return the highest score played for this game.
     *
     * @return String containing game high score
     */
    public String getHighScore();

    /**
     * This method should stop the timers, reset the score, and set a isRunning boolean value to false.
     */
    public void stopGame();

    /**
     * This method should return the current player's number of points.
     *
     * @return player's number of points
     */
    public int getPoints(); //add to spec

    /**
     * This method provides access to GameStats display for UserPanel to pass information to update score.
     * GameStats is created in Arcade; a reference should be passed to UserPanel (main panel) to update points.
     */
    public void setDisplay(GameStats d);
}
