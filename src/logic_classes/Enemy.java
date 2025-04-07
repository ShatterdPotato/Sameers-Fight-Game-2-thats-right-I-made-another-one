package logic_classes;
public class Enemy extends Character{

    public Enemy(int roundsWon) {
        super((int) (20 +  (roundsWon * 10)), ((int) (Math.random() * 5) + 1), "name");
        chooseName();
    }

    private void chooseName() {
        int random = (int) (Math.random() * 5) + 1;
        switch (random) {
            case 1:
                setName("Mr. Miller");
                setSprite("src\\sprites\\miller.png");
                break;
            case 2:
                setName("Skeleton");
                setSprite("src\\sprites\\skeleton.png");
                break;
            case 3:
                setName("Dwayne the Rock Johnson");
                setSprite("src\\sprites\\rock.png");
                break;
            case 4:
                setName("Evil Player");
                setSprite("src\\sprites\\evil_player.png");
                break;
            default:
                setName("Zombie");
                setSprite("src\\sprites\\zombie.png");
                break;
        }
    }
}
