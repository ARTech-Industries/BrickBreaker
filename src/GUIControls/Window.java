package GUIControls;

import javax.swing.*;
import java.awt.*;

public class Window {
    private static JFrame window;

    public Window() {
        window = new JFrame("");
        window.setContentPane(new JPanel());
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.decode("#cfcfcf"));
    }

    public static void setTitle(String title) {
        window.setTitle(title);
    }

    public static void setSize(int width, int height) {
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
    }

    public static void setResizeable(boolean isResizeable) {
        window.setResizable(isResizeable);
    }

    public static void setContentPane(JPanel panel) {
        window.getContentPane().removeAll();
        panel.setPreferredSize(new Dimension(window.getWidth(), window.getHeight()));
        window.setContentPane(panel);
        window.revalidate();
        window.repaint();
    }
}
