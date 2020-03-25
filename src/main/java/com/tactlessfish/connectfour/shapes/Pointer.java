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

public class Pointer extends Rectangle2D.Double {
    private static Properties properties = ConnectFour.getProperties();
    private Polygon triangle;
    private boolean isP1;
    private int col;

    /**
     * Constructs and initializes a Pointer
     * from the specified double coordinates.
     *
     * @param x the X coordinate of the upper-left corner
     *          of the newly constructed Pointer
     * @param y the Y coordinate of the upper-left corner
     *          of the newly constructed Pointer
     * @param w the width of the newly constructed
     *          Pointer
     * @param h the height of the newly constructed
     *          Pointer
     * @since 1.2
     */
    public Pointer(double x, double y, double w, double h) {
        super(x, y, w, h);
        triangle = constructTriangle();
        isP1 = true;
        col = 0;
    }

    public Polygon constructTriangle() {
        double triangleYOffset = getHeight() / 4.0;
        double triangleXOffset = getWidth() / 4.0;
        int[] triangleXCoordinates = {
                (int) getCenterX(),
                (int) (getCenterX() - triangleXOffset),
                (int) (getCenterX() + triangleXOffset)};
        int[] triangleYCoordinates = {
                (int) (getCenterY() + triangleYOffset),
                (int) (getCenterY() - triangleYOffset),
                (int) (getCenterY() - triangleYOffset)};
        return new Polygon(triangleXCoordinates, triangleYCoordinates, 3);
    }

    public void drawAll(Graphics2D graphics2D) {
        Color pointerColor = Color.decode(properties.getProperty("boardColor"));
        graphics2D.setColor(pointerColor);
        graphics2D.fill(this);

        graphics2D.setColor(Color.decode(properties.getProperty(isP1 ? "p1Color" : "p2Color")));
        graphics2D.fillPolygon(triangle);
    }

    public void moveLeft() {
        setRect(getX() - getWidth(), getY(), getWidth(), getHeight());
        triangle.translate((int) -getWidth(), 0);
        col--;
    }

    public void moveRight() {
        setRect(getX() + getWidth(), getY(), getWidth(), getHeight());
        triangle.translate((int) getWidth(), 0);
        col++;
    }
}
