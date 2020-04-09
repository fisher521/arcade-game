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
import java.awt.geom.Ellipse2D;
import java.util.Properties;

/**
 * Class representing a playable piece in Connect Four.
 */
public class Checker extends Ellipse2D.Double {
    private static Properties properties = ConnectFour.getProperties();
    private CheckerType type;

    /**
     * Constructs and initializes a Checker from the
     * specified coordinates, with type EMPTY.
     *
     * @param x the X coordinate of the upper-left corner
     *          of the framing rectangle
     * @param y the Y coordinate of the upper-left corner
     *          of the framing rectangle
     * @param w the width of the framing rectangle
     * @param h the height of the framing rectangle
     */
    public Checker(double x, double y, double w, double h) {
        super(x, y, w, h);
        type = CheckerType.EMPTY;
    }

    public Color getColor() {
        return type.getColor();
    }

    public CheckerType getType() {
        return type;
    }

    public void setType(CheckerType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case PLAYER1:
                return "1";
            case PLAYER2:
                return "2";
            default:
                return " ";
        }
    }

    public enum CheckerType {
        EMPTY(Color.decode(properties.getProperty("backgroundColor"))),
        PLAYER1(Color.decode(properties.getProperty("p1Color"))),
        PLAYER2(Color.decode(properties.getProperty("p2Color")));

        Color color;

        CheckerType(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
}
