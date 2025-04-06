import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<Weapon> weapons;
    private ArrayList<Food> foods;
    private int coins;

    public Player(int health, int attackDMG, String name) {
        super(health, attackDMG, name);
        weapons = new ArrayList<>();
        foods = new ArrayList<>();
        weapons.add(new Weapon("Wooden Sword", 2));
        coins = 0;
        try {
            setSprite(ImageIO.read(new File("src\\player.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void addWeapons(Weapon weapon) {
        weapons.add(weapon);
    }

    public boolean hasWeapon(String weaponName) {
        for (Weapon weapon: weapons) {
            if (weapon.getName().equals(weaponName)) {
                return true;
            }
        }
        return false;
    }


}
