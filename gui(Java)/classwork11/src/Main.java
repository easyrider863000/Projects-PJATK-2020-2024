import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TreeFrame();
        });
    }
}
class TreeFrame extends JFrame {
    public TreeFrame() {
        setSize(new Dimension(1024, 768));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g) {
        super.paint(g);
        // Punkt startowy
        int xFrom = 512;
        int yFrom = 768;
        // Długość gałęzi
        int radius = 100;
        // Początkowy kąt
        double angle = Math.PI;
        // Rysowanie
        draw(g, xFrom, yFrom, radius, angle);
    }
    private void draw(Graphics g, int xFrom, int yFrom, double radius, double angle) {
        double deltaAngle = 0.25;
        double leftEndX = xFrom + Math.sin(angle + deltaAngle)*radius;
        double leftEndY = yFrom + Math.cos(angle + deltaAngle)*radius;
        g.setColor(Color.BLACK);
        g.drawLine(xFrom, yFrom, (int)leftEndX, (int)leftEndY);
    }
}