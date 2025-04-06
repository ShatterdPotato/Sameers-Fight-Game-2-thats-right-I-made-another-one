import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel {
    private Player player;
    private JTextArea textArea;
    public EndScreen(Player player) {
        this.player = player;
        textArea = new JTextArea("So uhh you died and games over and uhhh i dont wanna code a retry or better close mechanic so close the game please :)\nYou also survived like " + player.getBattlesWon() + " rounds");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(0,0, 800, 600);
        textArea.setFont(new Font("Serif", Font.BOLD, 40));
        textArea.setOpaque(false);
        textArea.setFocusable(false);
        add(textArea);
    }

    public void paintComponent(Graphics g) {
        add(textArea);
    }
}
