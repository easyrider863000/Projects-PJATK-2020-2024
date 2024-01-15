package first;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new MyWindow();

        MyPanel myPanel = new MyPanel();
        myPanel.setContentPane(myPanel.getMainPanel());
        myPanel.pack();
        myPanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myPanel.setVisible(true);

    }
}
