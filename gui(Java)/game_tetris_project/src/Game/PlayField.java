package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class PlayField extends JPanel implements Serializable {
    private Item[][] items;
    private Shape currentShape;
    private Timer timerForMove;
    private int width;
    private int height;
    private int score;

    public PlayField(int widthItem, int heightItem) {
        this.width = widthItem;
        this.height = heightItem;
        this.score = 0;
        this.setSize(10* this.width, 22*this.height);
        this.setBackground(Color.white);
        startGame();
    }

    public void pauseGame(){
        timerForMove.stop();
    }
    public void continueGame(){
        timerForMove =  new Timer(500, (ActionEvent a) -> moveDown());
        timerForMove.start();
    }
    public void startGame(){
        items = new Item[10][22];
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                items[i][j] = new Item(Item.getDefaultItem().getColor(),this.width,this.height,true);
            }
        }
        addShape();
        timerForMove =  new Timer(500, (ActionEvent a) -> moveDown());
        timerForMove.start();
    }
    public void restartGame(){
        timerForMove.stop();
        startGame();
        score = 0;
    }

    public boolean isGameOver(){
        return currentShape == null || !timerForMove.isRunning();
    }

    public void addShape(){
        currentShape = new Shape(new Item(this.width,this.height,false),4,0);
        boolean isAdded = currentShape.getItemsWithShape(items);
        if(!isAdded){
            timerForMove.stop();
            currentShape = null;
        }
    }

    public void moveRight(){if(currentShape==null||!timerForMove.isRunning()){return;}currentShape.moveRight(items);}
    public void moveLeft(){if(currentShape==null||!timerForMove.isRunning()){return;}currentShape.moveLeft(items);}
    public void moveDown(){
        if(currentShape==null||!timerForMove.isRunning()){return;}
        boolean canMove = currentShape.moveDown(items);
        score++;
        if(!canMove) {
            int compLine = getLineCompleted();
            while (compLine != -1) {
                dropLine(compLine);
                compLine = getLineCompleted();
            }
            addShape();
        }

    }
    public void rotateLeft(){if(currentShape==null||!timerForMove.isRunning()){return;}currentShape.leftTransformShape(items);}


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < items[i].length; j++) {
                g.setColor(items[i][j].getColor());
                g.fill3DRect(5+i * this.width, 5+j * this.height, this.width, this.height, !items[i][j].isDefault());
            }
        }
    }
    public int getLineCompleted(){
        boolean flag;
        for (int i = 0; i < items[0].length; i++) {
            flag = true;
            for (int j = 0; flag&&j < items.length; j++) {
                if (items[j][i].isDefault()) {
                    flag = false;
                }
            }
            if(flag){
               return i;
            }
        }
        return -1;
    }
    public void dropLine(int y){
        for(int j = y; j>=1;j--) {
            for (int i = 0; i < items.length; i++) {
                items[i][j] = items[i][j - 1];
            }
        }
        for (int i = 0; i < items.length; i++) {
            items[0][i] = new Item(Item.getDefaultItem().getColor(),this.width,this.height,true);
        }
    }
    public void reSize(int width, int height){
        int i = 1;
        while(11*i+10<=width&&23*i+10<=height){
            i++;
        }
        this.width = i;
        this.height = i;
    }

    public int getScore() {
        return score;
    }
}
