import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<Item> inventory;
    private int coins;

    public Player(int health, int attackDMG, String name) {
        super(health, attackDMG, name);
        inventory = new ArrayList<>();
        inventory = new ArrayList<>();
        coins = 10;
        try {
            setSprite(ImageIO.read(new File("src\\player.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }


    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean hasWeapon(String weaponName) {
        for (Item item : inventory) {
            if ( (item instanceof Weapon) && item.getName().equals(weaponName)) {
                return true;
            }
        }
        return false;
    }


}
