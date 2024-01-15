package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class TetrisGame extends JFrame {
    PlayField playField;
    ControlPanel controlPanel;
    int bestScore;

    public TetrisGame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.setLayout(new GridLayout(1,3,0,0));
        this.setVisible(true);
        this.setTitle("Tetris game");
        this.setMinimumSize(new Dimension(300,335));
    }

    private void initComponents() {
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }
            @Override
            public void componentMoved(ComponentEvent e) {

            }
            @Override
            public void componentShown(ComponentEvent e) {

            }
            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        load();
        controlPanel = new ControlPanel(this);
        this.add(controlPanel);
        this.add(playField);
        this.setFocusable(true);
    }
    public void resize(){
        double width = this.getContentPane().getWidth();
        double height = this.getContentPane().getHeight();
        playField.reSize((int)(width/2),(int)height);
    }
    public void save(){
        try{
            FileOutputStream saveFile=new FileOutputStream("SaveGame.txt");

            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            save.writeObject(this.playField);
            save.writeObject(Math.max(this.playField.getScore(), this.bestScore));
            save.close();
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
    public void load(){
        File f = new File("SaveGame.txt");
        if(f.exists() && !f.isDirectory()) {
            try {
                FileInputStream saveFile = new FileInputStream("SaveGame.txt");
                ObjectInputStream save = new ObjectInputStream(saveFile);

                playField = (PlayField) save.readObject();
                bestScore = (int) save.readObject();
                save.close();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        return;
        }
        playField = new PlayField(30,30);
        bestScore = 0;
        playField.pauseGame();
    }

}
