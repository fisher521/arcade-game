package com.tactlessfish.connectfour;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Properties;

public class ConnectFourBoard extends Rectangle2D.Double {
    private static Properties properties = Arcade.getProperties();

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
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
    public ConnectFourBoard(double x, double y, double w, double h, double checkerDiameter) {
        super(x, y, w, h);

        checkers = new Checker[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                // TODO implement x and y
                checkers[row][col] = new Checker(0,0,
                        checkerDiameter, checkerDiameter, Checker.CheckerType.EMPTY);
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
