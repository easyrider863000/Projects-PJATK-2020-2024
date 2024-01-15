package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ControlPanel extends JPanel {
    private final JButton saveButton;
    private final JButton pauseButton;
    private final JButton continueButton;
    private final JButton restartButton;
    private final JLabel labelScore;
    private final JLabel labelBestScore;
    private final JLabel statusGame;
    private final TetrisGame gameObject;

    public ControlPanel(TetrisGame gameObject) {
        this.gameObject = gameObject;
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(8, 1));
        saveButton = new JButton("Save game");
        pauseButton = new JButton("Pause game");
        continueButton = new JButton("Continue game");
        restartButton = new JButton("Restart game");
        JButton exitButton = new JButton("Exit");
        labelScore = new JLabel("", JLabel.CENTER);
        labelBestScore = new JLabel("", JLabel.CENTER);
        statusGame = new JLabel("", JLabel.CENTER);
        this.add(statusGame);
        this.add(saveButton);
        this.add(pauseButton);
        this.add(continueButton);
        this.add(restartButton);
        this.add(exitButton);
        this.add(labelScore);
        this.add(labelBestScore);

        pauseButton.setEnabled(false);
        saveButton.setEnabled(false);
        gameObject.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        gameObject.playField.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        gameObject.playField.moveRight();
                        break;
                    case KeyEvent.VK_UP:
                        gameObject.playField.rotateLeft();
                        break;
                    case KeyEvent.VK_DOWN:
                        gameObject.playField.moveDown();
                        break;
                    case KeyEvent.VK_S:
                        saveGame();
                        break;
                    case KeyEvent.VK_R:
                        restartGame();
                        break;
                    case KeyEvent.VK_E:
                        exitGame();
                        break;
                    case KeyEvent.VK_P:
                        pauseGame();
                        break;
                    case KeyEvent.VK_C:
                        continueGame();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        pauseButton.addActionListener(e -> pauseGame());
        continueButton.addActionListener(e -> continueGame());
        restartButton.addActionListener(e -> restartGame());
        saveButton.addActionListener(e -> saveGame());
        exitButton.addActionListener(e -> exitGame());


        (new Timer(0, (ActionEvent a) -> {
            gameObject.playField.repaint();
            labelScore.setText("Your score: "+ gameObject.playField.getScore());
            if(gameObject.bestScore<gameObject.playField.getScore()) {
                gameObject.bestScore = gameObject.playField.getScore();
            }
            labelBestScore.setText("The best score: "+ gameObject.bestScore);
            if((gameObject.playField.isGameOver()&&pauseButton.isEnabled())||(!pauseButton.isEnabled()&&!continueButton.isEnabled())){
                statusGame.setText("Game Over!");
                pauseGame();
                continueButton.setEnabled(false);
            }
            else{
                statusGame.setText("Play!");
            }
            gameObject.requestFocus();
        })).start();
    }

    private void saveGame() {
        gameObject.save();
        saveButton.setEnabled(false);
    }
    private void pauseGame() {
        pauseButton.setEnabled(false);
        continueButton.setEnabled(true);
        restartButton.setEnabled(true);
        saveButton.setEnabled(true);
        gameObject.playField.pauseGame();
    }
    private void restartGame() {
        pauseButton.setEnabled(true);
        continueButton.setEnabled(false);
        restartButton.setEnabled(false);
        saveButton.setEnabled(false);
        gameObject.playField.restartGame();
    }
    private void continueGame() {
        pauseButton.setEnabled(true);
        continueButton.setEnabled(false);
        restartButton.setEnabled(false);
        saveButton.setEnabled(false);
        gameObject.playField.continueGame();
    }
    private void exitGame() {
        System.exit(0);
    }

}