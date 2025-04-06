import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Shop {
    private BufferedImage sprite;
    private ArrayList<Weapon> arsenal;
    private Player player;

    public Shop(Player player) {
        try {
            sprite = ImageIO.read(new File("src\\shop.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.player = player;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int fetchPrice(String req) {
        switch (req) {
            case "food":
                return 5;
            case "wooden sword":
                if (!player.hasWeapon("Wooden Sword")) {
                    return 10;
                }
                return -1;
            case "gold scar":
                if (!player.hasWeapon("Gold Scar")) {
                    return 35;
                }
                return -1;
            case "dark magic for dummies":
                if (!player.hasWeapon("Dark Magic for Dummies")) {
                    return 65;
                }
                return -1;
            case "legendary zenith":
                if (!player.hasWeapon("Legendary Zenith")) {
                    return 100;
                }
                return -1;
            case "alarm clock":
                if (!player.hasWeapon("Alarm Clock")) {
                    return 20;
                }
                return -1;
            case "college rejection letter":
                if (!player.hasWeapon("College Rejection Letter")) {
                    return 45;
                }
                return -1;
            default:
                return -2;
        }
/*        if (req.equalsIgnoreCase("food")) {
            player.addFood(generateFood());
            return true;
        }   else {
            if (checkArsenal(req) != null) {
                player.addWeapons(checkArsenal(req));
                return true;
            }
        }
        return false;*/
    }

    private Food generateFood() {
        int rand = (int) (Math.random() * 10) + 1;
        switch (rand) {
            case 1:
                return new Food("McFlurry", 4);
            case 2:
                return new Food("Big Mac", 15);
            case 3:
                return new Food("Large Fries", 7);
            case 4:
                return new Food("10 pc. McNuggets", 5);
            case 5:
                return new Food("McChicken", 8);
            case 6:
                return new Food("McRib", 15);
            case 7:
                return new Food("McMuffin", 7);
            case 8:
                return new Food("Ice Cream", 2);
            case 9:
                return new Food("Filet o Fish", 12);
            default:
                return new Food("Cookie", 1);
        }
    }

    private void stockArsenal(String req) {
        arsenal = new ArrayList<>();
        arsenal.add(new Weapon("Wooden Sword", 2));
        arsenal.add(new Weapon("Gold Scar", 5));
        arsenal.add(new Weapon("Dark Magic for Dummies", 8));
        arsenal.add(new Weapon("Legendary Zenith", 15));
        arsenal.add(new Weapon("Alarm Clock", 7 ));
        arsenal.add(new Weapon("College Rejection Letter", 5));
    }

    private String returnWeaponName(int price) {
        switch (price) {
            case 10:
                return "Wooden Sword";
            case 35:
                return "Gold Scar";
            case 65:
                return "Dark Magic for Dummies";
            case 100:
                return "Legendary Zenith";
            case 20:
                return "Alarm Clock";
            case 45:
                return "College Rejection Letter";
            default:
                return "";
        }
    }

    private Weapon checkArsenal(int price) {
        String weapon = returnWeaponName(price);
        for (int i = 0; i < arsenal.size(); i++) {
            if (arsenal.get(i).getName().equals(weapon)) {
                return arsenal.remove(i);
            }
        }
        return null;
    }

    public void buyItem(int price) {
        if (price == 5) {
            player.addFood(generateFood());
        }   else {
            player.addWeapons(checkArsenal(price));
        }
    }
}
