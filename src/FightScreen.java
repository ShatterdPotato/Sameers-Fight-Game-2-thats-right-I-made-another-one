import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightScreen extends JPanel implements ActionListener {
    private JButton fightButton;
    private JButton healButton;
    private Player player;
    private Enemy enemy;

    public FightScreen(Player player) {
        fightButton = new JButton("Attack");
        healButton = new JButton("Heal");
        fightButton.addActionListener(this);
        healButton.addActionListener(this);
        add(fightButton);
        add(healButton);
        this.player = player;
        enemy = new Enemy();
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
