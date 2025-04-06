import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<Item> inventory;
    private int coins;
    private int battelesWon;

    public Player(int health, int attackDMG, String name) {
        super(health, attackDMG, name);
        inventory = new ArrayList<>();
        coins = 20;
        battelesWon = 0;
        setSprite("src\\player.png");
    }

    public int getCoins() {
        return coins;
    }

    public int getBattlesWon() {
        return battelesWon;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void incrementBattlesWon() {
        battelesWon++;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean hasFood() {
        for (Item item : inventory) {
            if (item instanceof Food) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWeapon(String weaponName) {
        for (Item item : inventory) {
            if ( (item instanceof Weapon) && item.getName().equals(weaponName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWeapon() {
        for (Item item : inventory) {
            if (item instanceof Weapon) {
                return true;
            }
        }
        return false;
    }


}
