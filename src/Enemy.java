public class Enemy extends Character{

    public Enemy() {
        super(20, ((int) (Math.random() * 5) + 1), "name");
    }

    //generates a random name for the enemy to add variety
    private void chooseName() {
        int random = (int) (Math.random() * 5) + 1;
        switch (random) {
            case 1:
                setName("Zombie");
            case 2:
                setName("Skeleton");
            case 3:
                setName("Mummy");
            case 4:
                setName("Spider");
            default:
                setName("Ghoul");
        }
    }
}
