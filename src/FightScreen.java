import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightScreen extends JPanel implements ActionListener {
    private JButton fightButton;
    private JButton healButton;
    private String statusTXT;
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
        statusTXT = "It seems that a " + enemy.getName() + " dared enter the presence of " + player.getName();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawString(statusTXT, 0,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fightButton) {
            enemy.attacked(player.getAttackDMG());
            statusTXT = "YOUCH! " + player.getName() + " has just struck " + enemy.getName() + " for a whopping " + player.getAttackDMG();
        }   else if (e.getSource() == healButton) {
            player.heal();
            statusTXT = "You have decided to heal for " + player.getHealth() + " HP!";
        }
        repaint();
        validate();
    }
}
