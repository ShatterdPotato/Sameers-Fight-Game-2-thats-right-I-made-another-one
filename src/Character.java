import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Character {
    //instance variables
    private int health;
    private int attackDMG;
    private String name;
    private BufferedImage sprite;

    public Character(int health, int attackDMG, String name) {
        this.health = health;
        this.attackDMG = attackDMG;
        this.name = name;
    }
    //getters
    public int getHealth() {
        return health;
    }

    public int getAttackDMG() {
        return attackDMG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSprite(BufferedImage image) {
        sprite = image;
    }

    //increments the enemy's HP by given amount
    public void heal(int healAmount) {
        health += healAmount;
    }

    //overloaded heal method that generates the heal amount in the method
    public void heal() {
        health += (int) (Math.random() * 20) + 1;
    }

    //decrements the enemy's HP by given amount
    public void attacked(int DMGAmount) {
        health -= DMGAmount;
    }

    //overloaded attack method that generates the DMG amount in the method
    public void attacked() {
        health -= (int) (Math.random() * 20) + 1;
    }
}
