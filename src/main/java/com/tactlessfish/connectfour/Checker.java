package com.tactlessfish.connectfour;

import java.awt.geom.Ellipse2D;

public class Checker extends Ellipse2D.Double {
    /**
     * Constructs and initializes a <code>Checker</code> from the
     * specified coordinates.
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
    }
}
