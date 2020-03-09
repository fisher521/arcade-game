package com.tactlessfish.connectfour;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Arcade extends JFrame {
    public Arcade() throws IOException {
        super("AP Java Arcade");

        Properties properties = findProperties();

        JavaArcade game = new UserPanel(600, 450, properties);

        //passing in a JavaArcade, therefore I know I can call getHighScore(), getScore()
        GameStats display = new GameStats(game);

        //Also passing in JavaArcade to ControlPanel, I know you will respond to buttons
        ControlPanel controls = new ControlPanel(game, display);

        game.setDisplay(display); //provides game ability to update display

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.add(display, BorderLayout.NORTH);
        panel.add((JPanel) game, BorderLayout.CENTER);
        panel.add(controls, BorderLayout.SOUTH);

        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);
    }

    private Properties findProperties() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    public static void main(String[] args) throws IOException {
        Arcade window = new Arcade();
        window.setBounds(100, 100, 600, 600);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
