package com.tactlessfish.connectfour;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Arcade extends JFrame {
    private static Properties properties;

    public Arcade() throws IOException {
        super("AP Java Arcade");

        properties = initializeProperties();

        JavaArcade game = new UserPanel(600, 450);
        GameStats display = new GameStats(game);
        // Passing in a JavaArcade to ControlPanel allows response to buttons
        ControlPanel controls = new ControlPanel(game, display);
        game.setDisplay(display); // Provides game ability to update display

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.add(display, BorderLayout.NORTH);
        panel.add((JPanel) game, BorderLayout.CENTER);
        panel.add(controls, BorderLayout.SOUTH);

        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);
    }

    private Properties initializeProperties() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void main(String[] args) throws IOException {
        Arcade window = new Arcade();
        window.setBounds(100, 100, 600, 600);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
