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

package com.tactlessfish.connectfour.shapes;

import com.tactlessfish.connectfour.ConnectFour;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Properties;

public class ConnectFourBoard extends Rectangle2D.Double {
    private static Properties properties = ConnectFour.getProperties();

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final double CHECKER_CELL_RATIO = 0.667;

    private double cellDiameter;
    private Checker[][] checkers;

    private int latestRow;
    private int latestCol;

    /**
     * Constructs and initializes a ConnectFourBoard
     * from the specified double coordinates.
     *
     * @param x the X coordinate of the upper-left corner
     *          of the newly constructed ConnectFourBoard
     * @param y the Y coordinate of the upper-left corner
     *          of the newly constructed ConnectFourBoard
     * @param w the width of the newly constructed
     *          ConnectFourBoard
     * @param h the height of the newly constructed
     *          ConnectFourBoard
     */
    public ConnectFourBoard(double x, double y, double w, double h) {
        super(x, y, w, h);

        cellDiameter = getHeight() / ROWS;
        double checkerDiameter = cellDiameter * CHECKER_CELL_RATIO;
        double circleShift = (1 - (1 - CHECKER_CELL_RATIO) / 2) * cellDiameter;

        checkers = new Checker[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                double checkerX = getX() + (cellDiameter * (col + 1)) - circleShift;
                double checkerY = getY() + (cellDiameter * (row + 1)) - circleShift;

                checkers[row][col] = new Checker(checkerX, checkerY,
                        checkerDiameter, checkerDiameter);
            }
        }
    }

    /**
     * Draws the board and all checkers.
     *
     * @param graphics2D Graphics2D object used to draw
     */
    public void drawAll(Graphics2D graphics2D) {
        graphics2D.setColor(Color.decode(properties.getProperty("boardColor")));
        graphics2D.fill(this);

        for (Checker[] row : checkers) {
            for (Checker checker : row) {
                graphics2D.setColor(checker.getColor());
                graphics2D.fill(checker);
            }
        }
    }


    /**
     * Places a checker on the bottommost row of a specified column.
     *
     * @param isP1 if player placing the checker is P1
     * @param col  the column to place the checker
     * @return true if successful, false if unsuccessful
     */
    public boolean placeChecker(boolean isP1, int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (checkers[row][col].getType() == Checker.CheckerType.EMPTY) {
                checkers[row][col].setType(isP1
                        ? Checker.CheckerType.PLAYER1
                        : Checker.CheckerType.PLAYER2);
                latestRow = row;
                latestCol = col;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin() {
        String player = checkers[latestRow][latestCol].toString();
        String fourInARow = String.join("", player, player, player, player);

        return getHorizontalString().contains(fourInARow)
                || getColumnString().contains(fourInARow)
                || getDiagonalString(true).contains(fourInARow)
                || getDiagonalString(false).contains(fourInARow);
    }

    /**
     * @return string representation of the row containing the latest checker
     */
    private String getHorizontalString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Checker checker : checkers[latestRow]) {
            stringBuilder.append(checker);
        }
        return stringBuilder.toString();
    }

    /**
     * @return string representation of the column containing the latest checker
     */
    private String getColumnString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0; row < ROWS; row++) {
            stringBuilder.append(checkers[row][latestCol]);
        }
        return stringBuilder.toString();
    }

    /**
     * @param isLeftToRight true if moving left to right "/", false if moving right to left "\"
     * @return string representation of a diagonal containing the latest checker
     */
    private String getDiagonalString(boolean isLeftToRight) {
        StringBuilder stringBuilder = new StringBuilder(ROWS);

        for (int row = ROWS - 1; row >= 0; row--) {
            int col = isLeftToRight
                    ? latestCol + latestRow - row
                    : latestCol - latestRow + row;

            if (col >= 0 && col < COLUMNS) {
                stringBuilder.append(checkers[row][col]);
            }
        }
        return stringBuilder.toString();
    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }

    public double getCellDiameter() {
        return cellDiameter;
    }
}
