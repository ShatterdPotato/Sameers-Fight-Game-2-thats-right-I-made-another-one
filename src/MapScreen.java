import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapScreen extends JPanel implements KeyListener {
    private Map map;
    private Player player;

    public MapScreen(Player player) {
        this.map = new Map(player);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.getHSBColor(1.28f, 1.0f, 0.58f));
        g2d.fillRect(0, 0, 800, 600);
        g2d.drawImage(player.getSprite(), map.getPlayerCoords()[0] * 100, map.getPlayerCoords()[1] * 100, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        map.movePlayer(e.getKeyCode());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
