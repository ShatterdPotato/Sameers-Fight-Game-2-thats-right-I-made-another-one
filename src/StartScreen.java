import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StartScreen extends JPanel implements ActionListener {
    private JButton continueButton;
    private JButton yesButton;
    private JButton noButton;
    private String currMessage;
    private ArrayList<String> dialouge;
    private int diagTracker;
    private JTextField nameBox;

    public StartScreen() {
        add(continueButton = new JButton("Continue"));
        continueButton.addActionListener(this);
        nameBox = new JTextField(10);
        compileDialogue();
        currMessage = dialouge.getFirst();
    }

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
            }
            switch (diagTracker) {
                case 2:
                    nameBox.setVisible(true);
                    add(nameBox);
                    currMessage = dialouge.get(diagTracker);
                    break;
                case 3:
                    String name = nameBox.getText();
                    nameBox.setVisible(false);
                    currMessage = name + dialouge.get(diagTracker);
                    break;
                case 4:
                    continueButton.setVisible(false);
                    add(yesButton = new JButton("Yes"));
                    yesButton.addActionListener(this);
                    add(noButton = new JButton("No"));
                    noButton.addActionListener(this);
                    currMessage = dialouge.get(diagTracker);
                    break;
                case 6:
                    break;
                case 7:
                    if (continueButton.getText().equals("Start Game")) {
                        GameWindow.cycleScreen(new MapScreen(new Map()));
                    }
                    continueButton.setText("Start Game");
                default:
                    currMessage = dialouge.get(diagTracker);
            }
        }   else if (e.getSource() == yesButton) {
            yesButton.setVisible(false);
            noButton.setVisible(false);
            continueButton.setVisible(true);
            diagTracker = 5;
            continueButton.setText("View Tutorial");
            currMessage = dialouge.get(diagTracker);
        }   else if (e.getSource() == noButton) {
            noButton.setVisible(false);
            yesButton.setVisible(false);
            continueButton.setVisible(true);
            diagTracker = 6;
            currMessage = dialouge.get(diagTracker);
        }
        validate();
        repaint();
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
