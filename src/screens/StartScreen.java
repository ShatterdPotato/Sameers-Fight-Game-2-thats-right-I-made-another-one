package screens;
import logic_classes.Player;

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
    private JTextArea currMessage;
    private ArrayList<String> dialouge;
    private int diagTracker;
    private JTextField nameBox;
    private Player player;

    public StartScreen() {
        add(continueButton = new JButton("Continue"));
        continueButton.addActionListener(this);
        nameBox = new JTextField(10);
        yesButton = new JButton("Yes");
        yesButton.addActionListener(this);
        noButton = new JButton("No");
        noButton.addActionListener(this);
        compileDialogue();
        currMessage = new JTextArea(dialouge.getFirst());
        currMessage.setEditable(false);
        currMessage.setLineWrap(true);
        currMessage.setOpaque(false);
        currMessage.setFocusable(false);
        currMessage.setWrapStyleWord(true);
        currMessage.setBounds(20,0, 760, 400);

        currMessage.setFont(new Font("Arial", Font.BOLD, 16));
        add(currMessage);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        continueButton.setLocation(400, 200);
        nameBox.setLocation(200, 200);
        yesButton.setLocation(200, 200);
        noButton.setLocation(300, 200);
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
                    currMessage.setText(dialouge.get(diagTracker));
                    break;
                case 3:
                    String name = nameBox.getText();
                    player = new Player(20, 0, name);
                    nameBox.setVisible(false);
                    currMessage.setText(name + dialouge.get(diagTracker));
                    break;
                case 4:
                    continueButton.setVisible(false);
                    add(yesButton);
                    add(noButton);
                    currMessage.setText(dialouge.get(diagTracker));
                    break;
                case 6:
                    break;
                case 7:
                    if (continueButton.getText().equals("Start Game")) {
                        GameWindow.cycleScreen(new MapScreen(player));
                    }
                    continueButton.setText("Start Game");
                default:
                    currMessage.setText(dialouge.get(diagTracker));
            }
        }   else if (e.getSource() == yesButton) {
            yesButton.setVisible(false);
            noButton.setVisible(false);
            continueButton.setVisible(true);
            diagTracker = 5;
            continueButton.setText("View Tutorial");
            currMessage.setText(dialouge.get(diagTracker));
        }   else if (e.getSource() == noButton) {
            noButton.setVisible(false);
            yesButton.setVisible(false);
            continueButton.setVisible(true);
            diagTracker = 6;
            currMessage.setText(dialouge.get(diagTracker));
        }
        validate();
        repaint();
    }

    private void compileDialogue() {
        dialouge = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File("src\\lang\\dialogue.txt"));
            while (fileScanner.hasNext()) {
                dialouge.add(fileScanner.nextLine());
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
