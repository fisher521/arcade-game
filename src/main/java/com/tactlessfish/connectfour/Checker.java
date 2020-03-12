package com.tactlessfish.connectfour;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Properties;

public class Checker extends Ellipse2D.Double {
    private CheckerType type;
    private static Properties properties = Arcade.getProperties();

    /**
     * Constructs and initializes a Checker from the
     * specified coordinates.
     *
     * @param x the X coordinate of the upper-left corner
     *          of the framing rectangle
     * @param y the Y coordinate of the upper-left corner
     *          of the framing rectangle
     * @param w the width of the framing rectangle
     * @param h the height of the framing rectangle
     */
    public Checker(double x, double y, double w, double h, CheckerType type) {
        super(x, y, w, h);
        this.type = type;
    }

    public Color getColor() {
        return type.getColor();
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
