package logic_classes;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character {

    private int health;
    private int maxHealth;
    private int attackDMG;
    private String name;
    private BufferedImage sprite;

    public Character(int health, int attackDMG, String name) {
        this.health = health;
        this.maxHealth = health;
        this.attackDMG = attackDMG;
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackDMG() {
        return attackDMG;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSprite(String path) {
        try{
            sprite = ImageIO.read(new File(path));
        }   catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void heal(int healAmount) {
        health += healAmount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    //decrements the enemy's HP by given amount
    public void attacked(int DMGAmount) {
        health -= DMGAmount;
        if (health <= 0) {
            health = 0;
        }
    }

}
