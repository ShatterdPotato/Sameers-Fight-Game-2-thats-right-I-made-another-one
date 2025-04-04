import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shop {
    private BufferedImage sprite;

    public Shop() {
        try {
            sprite = ImageIO.read(new File("src\\shop.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
