import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SameerFightGameLogic extends JPanel implements ActionListener {
    private Player player;
    private Enemy enemy;
    private Map world;
    private Shop shop;
    private JButton continueButton;
    private String currMessage;
    private ArrayList<String> dialouge;
    private int diagTracker;
    private JTextField nameBox;

    public SameerFightGameLogic() {
        continueButton = new JButton("Continue");
        continueButton.addActionListener(this);
        nameBox = new JTextField(10);
        add(continueButton);
        add(nameBox);
        nameBox.setVisible(false);
        compileDialogue();
        diagTracker = 0;
        currMessage = dialouge.get(diagTracker);
    }

/*    public void run() {
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

    }*/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString(currMessage, 20, 20);
        continueButton.setLocation(getWidth() / 2 - 100, 20);
        nameBox.setLocation(100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton) {
            if (diagTracker + 1 < dialouge.size()) {
                diagTracker++;
                if(diagTracker >= 2) {
                    nameBox.setVisible(true);
                    System.out.println("here");
                }
            }
            currMessage = dialouge.get(diagTracker);

            validate();
            repaint();
        }
    }

    private void compileDialogue() {
        dialouge = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File("src\\dialogue.txt"));
            while (fileScanner.hasNext()) {
                dialouge.add(fileScanner.nextLine());
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
