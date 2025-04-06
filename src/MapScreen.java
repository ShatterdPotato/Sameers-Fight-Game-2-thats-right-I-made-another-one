import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class MapScreen extends JPanel implements KeyListener {
    private Map map;
    private Player player;
    private Shop shop;

    public MapScreen(Player player) {
        shop = new Shop(player);
        this.map = new Map(player, shop);
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
        drawMap(g2d);
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
    private void drawMap(Graphics2D g2d) {
        BufferedImage bush = null;
        BufferedImage enemyBush = null;
        try {
            bush = ImageIO.read(new File("src\\bush.png"));
            enemyBush = ImageIO.read(new File("src\\enemy_bush.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g2d.drawImage(shop.getSprite(), 600, 200, null);
        g2d.drawImage(player.getSprite(), map.getPlayerCoords()[0] * 100, map.getPlayerCoords()[1] * 100, null);
        for (int i = 0; i < map.getMapGrid().length; i++) {
            for (int j = 0; j < map.getMapGrid()[0].length; j++) {
                switch(map.getMapGrid()[i][j]) {
                    case '*':
                        g2d.drawImage(bush, i * 100, j * 100, null);
                        break;
                    case 'X':
                        g2d.drawImage(enemyBush, i * 100, j * 100, null);
                        break;
                }
            }
        }
    }
    private void paintPlayerStats(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0,0, 200, 100);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(20, 20, 160, 60);
    }
}
