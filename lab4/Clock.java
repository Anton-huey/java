package lab4;

import javax.swing.*;

public class Clock {
    private static final String TITLE = "ClOCK";

    public static void main() {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ClockPanel());
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
