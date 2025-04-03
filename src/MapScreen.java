import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapScreen extends JPanel implements KeyListener {
    private Map map;
    private Player player;

    public MapScreen(Map map, Player player) {
        this.map = map;
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
        g2d.fillRect(0, 0,800,600);
        paintPlayer(g2d);
    }

    private void paintPlayer(Graphics2D g2d) {
        try {
            BufferedImage player = ImageIO.read(new File("src\\player.png"));
            g2d.drawImage(player, map.getPlayerCoords()[0] * 100, map.getPlayerCoords()[1] * 100, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

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
