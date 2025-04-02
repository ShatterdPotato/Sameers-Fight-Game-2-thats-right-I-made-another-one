import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Player extends Character{

    public Player(int health, int attackDMG, String name) {
        super(health, attackDMG, name);
        try {
            setSprite(ImageIO.read(new File("src\\player.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
