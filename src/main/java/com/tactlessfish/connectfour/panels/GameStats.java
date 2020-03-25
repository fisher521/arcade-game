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

import java.awt.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Class representing current game stats.
 */
public class GameStats extends JPanel {
    private static Properties properties = ConnectFour.getProperties();

    private JTextField gameNameText;
    private JTextField currentHighScorer;
    private JTextField currentHighScore;

    private int yourScore;
    private JLabel yourScoreText;
    private JavaArcade javaArcade;

    // Constructor
    public GameStats(JavaArcade javaArcade) {
        super(new GridLayout(2, 4, 10, 0));
        setBorder(new EmptyBorder(0, 0, 5, 0));
        Font gameNameFont = new Font(properties.getProperty("titleFont"), Font.BOLD, 32);

        JLabel gName = new JLabel(" " + javaArcade.getGameName());

        gName.setForeground(Color.decode(properties.getProperty("titleColor")));
        gName.setFont(gameNameFont);
        add(gName);

        add(new JLabel(" Current High Score:   " + javaArcade.getHighScore()));
        add(new JLabel(" "));

        yourScoreText = new JLabel(" Your Final Score: " + 0);
        add(yourScoreText);
        Font displayFont = new Font("Monospaced", Font.BOLD, 16);

        this.javaArcade = javaArcade;
    }

    public void update(int points) {
        yourScoreText.setText(" Your Score: " + points);
    }

    public void gameOver(int points) {
        if (points > Integer.parseInt(javaArcade.getHighScore())) {
            yourScoreText.setForeground(Color.BLUE);
            String s = (String) JOptionPane.showInputDialog(this,
                    "You are the new high scorer. Congratulations!\n Enter your name: ",
                    "High Score", JOptionPane.PLAIN_MESSAGE,
                    null, null, "name");
        }
    }
}
