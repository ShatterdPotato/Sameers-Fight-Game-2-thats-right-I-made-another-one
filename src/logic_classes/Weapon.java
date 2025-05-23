package logic_classes;

import java.awt.image.BufferedImage;

public class Weapon extends Item {
    private int damage;
    private BufferedImage sprite;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
