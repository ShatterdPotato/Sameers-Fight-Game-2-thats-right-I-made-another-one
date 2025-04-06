public class Food extends Item{
    private int healAMT;
    public Food(String name, int HP) {
        super(name);
        healAMT = HP;
    }

    public int getHealAMT() {
        return healAMT;
    }
}
