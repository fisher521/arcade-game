package com.tactlessfish.connectfour;

import java.awt.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Class representing current game stats.
 */
public class GameStats extends JPanel {
    private static Properties properties = Arcade.getProperties();

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
        Font gameNameFont = new Font(properties.getProperty("titleFont"), Font.BOLD, 24);

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
