import java.util.Scanner;

public class SameerFightGameLogic {
    private Player player;
    private Enemy enemy;
    private Map world;
    private Shop shop;
    private Scanner scanner;
    public SameerFightGameLogic() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to Sameer's Fight Game!\n");
        System.out.print("This is a sequel to Sameer's Fight Game with added features, \nand a new remastered approach to the original game!\n(Press enter to continue):");
        scanner.nextLine();
        System.out.print("\nPlease enter your name: ");
        String name = scanner.nextLine();
        player = new Player(20, 0, name);
        System.out.printf("%s huh? Weird name.\nOh well it doesn't matter, because your adventure begins NOW!\n(Press enter to continue):", name);
        scanner.nextLine();
        while (true) {
            System.out.print("\nWould you like to play through the tutorial before starting? (y/n): ");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y")) {
                System.out.println("Okay! One private void tutorial() call coming right up!");
                break;
            }   else if (answer.equals("n")) {
                System.out.println("Oh, ok, i guess those hours I spent making the tutorial aren't worth it because SOMEONE put on their big boy pants and though \" OhHhHhHhHhH i CaN dO tHiS mYsElF bEcAuSe I gO tO BrOoKlYn TeCh\"");
                System.out.print("Fine have fun then i guess\n(Press enter to continue without my immaculate, pristine, Divine-quality, hand crafted tutorial 😠):");
                break;
            }   else {
                System.out.print(Misc.extraneousResponse());
            }
        }

    }

}
