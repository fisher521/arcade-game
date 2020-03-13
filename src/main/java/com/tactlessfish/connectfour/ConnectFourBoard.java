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

package com.tactlessfish.connectfour;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Properties;

public class ConnectFourBoard extends Rectangle2D.Double {
    private static Properties properties = Arcade.getProperties();

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final double CHECKER_CELL_RATIO = 0.667;

    private Checker[][] checkers;

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

        double cellDiameter = getHeight() / ROWS;
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

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }
}
