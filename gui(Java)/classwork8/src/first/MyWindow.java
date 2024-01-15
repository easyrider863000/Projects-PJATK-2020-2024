package first;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    public MyWindow() {
        super();
        this.setSize(1024, 768);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(new FlowLayout());
        initComponents();
        setVisible(true);


    }

    private void initComponents() {
        this.add(new MyPanel().getMainPanel());
    }
}
