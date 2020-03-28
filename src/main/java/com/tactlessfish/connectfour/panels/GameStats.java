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

    private JLabel yourScoreText;
    private JLabel highScoreText;
    private JavaArcade javaArcade;

    public GameStats(JavaArcade javaArcade) {
        super(new GridLayout(2, 4, 10, 0));
        setBorder(new EmptyBorder(0, 0, 5, 0));

        Font gameNameFont = new Font(properties.getProperty("titleFont"), Font.BOLD, 32);
        JLabel gName = new JLabel(" " + javaArcade.getGameName());
        gName.setForeground(Color.decode(properties.getProperty("titleColor")));
        gName.setFont(gameNameFont);
        add(gName);

        highScoreText = new JLabel(" Fewest Checkers Placed: " + javaArcade.getHighScore());
        add(highScoreText);
        add(new JLabel(" "));

        yourScoreText = new JLabel(" Your Final Score: " + 0);
        add(yourScoreText);
        Font displayFont = new Font("Monospaced", Font.BOLD, 16);

        this.javaArcade = javaArcade;
    }

    public void update(int points) {
        yourScoreText.setText(" Checkers Placed: " + points);
    }

    public void gameOver(int points) {
        if (points < Integer.parseInt(javaArcade.getHighScore())) {
            yourScoreText.setForeground(Color.BLUE);
            String s = (String) JOptionPane.showInputDialog(this,
                    "You are the new high scorer. Congratulations!\n Enter your name: ",
                    "Fewest Checkers Placed", JOptionPane.PLAIN_MESSAGE,
                    null, null, "name");
            ((UserPanel) javaArcade).writeHighScore(points + "");
            highScoreText.setText(" Fewest Checkers Placed: " + points);
        }
    }
}
