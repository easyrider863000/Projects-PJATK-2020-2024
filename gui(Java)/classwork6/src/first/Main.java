package first;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Okno okno = new Okno(100,100,20);
    }
}

class Okno extends JFrame {
    private int x;
    private int y;
    private int r;
    Okno(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.setSize(500,500);
        this.setVisible(true);
        this.setTitle("Hello world");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){
        //int width = 70;
        //int height = 70;
        //g.clearRect(0,0,this.getWidth(), this.getHeight());
        g.setColor(new Color(250,100,4));
        //g.drawOval(this.getWidth()/2-(width/2),this.getHeight()/2-(height/2),width,height);
        g.drawOval(this.x,this.y,this.r*2,this.r*2);
    }
}
