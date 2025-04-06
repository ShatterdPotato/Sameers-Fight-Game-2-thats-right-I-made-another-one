public class Enemy extends Character{

    public Enemy() {
        super(20, ((int) (Math.random() * 5) + 1), "name");
        chooseName();
    }

    //generates a random name for the enemy to add variety
    private void chooseName() {
        int random = (int) (Math.random() * 5) + 1;
        switch (random) {
            case 1:
                setName("Mr. Miller");
                setSprite("src\\miller.png");
                break;
            case 2:
                setName("Skeleton");
                setSprite("src\\skeleton.png");
                break;
            case 3:
                setName("Dwayne the Rock Johnson");
                setSprite("src\\rock.png");
                break;
            case 4:
                setName("Evil Player");
                setSprite("src\\evil_player.png");
                break;
            default:
                setName("Zombie");
                setSprite("src\\zombie.png");
                break;
        }
    }
}
