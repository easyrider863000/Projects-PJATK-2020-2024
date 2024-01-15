import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args){
        Okno okno = new Okno();
        //MyThread thread = new MyThread(okno);
        //thread.start();
        Thread th = new Thread(()->{
            for (int i = 0; i < 500; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                okno.draw(i,i);
            }
        });
        th.start();

        while(true){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            okno.repaint();
        }
    }
}

class MyThread extends Thread{
    Okno okno;
    public MyThread(Okno okno){
        this.okno = okno;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            okno.draw(i,i);
        }
    }
}

class Okno extends JFrame {
    private int x;
    private int y;
    public Okno(){
        Timer timer = new Timer(10,this::actionPerformed);
        timer.start();
        this.setSize(500,500);
        this.setVisible(true);
        this.setTitle("Hello world");
        x = 100;
        y = 100;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){

    }

    public void draw(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void paint(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(new Color(250,100,4));
        g.drawRect(x,y,30,30);
    }
}
