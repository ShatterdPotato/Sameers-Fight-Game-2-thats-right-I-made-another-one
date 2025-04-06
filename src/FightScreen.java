import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FightScreen extends JPanel implements ActionListener, KeyListener {
    private JButton fightButton;
    private JButton healButton;
    private String statusTXT;
    private JLabel continueTXT;
    private Player player;
    private Enemy enemy;
    private boolean playerTurn;

    public FightScreen(Player player) {
        setLayout(null);
        fightButton = new JButton("Attack");
        healButton = new JButton("Heal");
        fightButton.addActionListener(this);
        healButton.addActionListener(this);
        fightButton.setBounds(300, 300, 10, 40);
        add(fightButton);
        add(healButton);
        this.player = player;
        enemy = new Enemy();
        statusTXT = "It seems that a " + enemy.getName() + " dared enter the presence of " + player.getName();
        continueTXT = new JLabel("Press any button to continue");
        add(continueTXT);
        playerTurn = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        drawInfo(g);
        g.setColor(Color.BLACK);
        g.drawImage(player.getSprite(), 100, 300, null);
        g.drawString(statusTXT, 40,500);
        fightButton.setLocation(300, 300);
        healButton.setLocation(400, 300);
        //continueTXT.setLocation(300, 600);
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

    private void drawInfo(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 400, 800, 200);
        g.setColor(Color.GRAY);
        g.fillRect(20, 420, 760, 160);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,200, 100);
    }

    private void handleTurns() {
        if (playerTurn) {
            fightButton.setVisible(false);
            healButton.setVisible(false);
        }   else {
            fightButton.setVisible(true);
            healButton.setVisible(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
/*        if (continueTXT.isVisible()) {
            continueTXT.setVisible(false);
            System.out.println("Hi");
            handleTurns();
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
