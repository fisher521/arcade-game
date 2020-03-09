package com.tactlessfish.connectfour;

import java.awt.geom.Rectangle2D;

public class ConnectFourBoard extends Rectangle2D.Double {
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
    public ConnectFourBoard(double x, double y, double w, double h, Checker[][] checkers) {
        super(x, y, w, h);
        this.checkers = checkers;
    }
}
