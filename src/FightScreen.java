import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FightScreen extends JPanel implements ActionListener, KeyListener {
    private JButton fightButton;
    private JButton healButton;
    private JTextArea statusTXT;
    private JLabel playerHP;
    private JLabel enemyHP;
    private JLabel continueTXT;
    private Player player;
    private Enemy enemy;
    private boolean playerTurn;
    private boolean won;
    private boolean lost;

    public FightScreen(Player player) {
        this.player = player;
        player.heal((int) (Math.random() * 5) + 1);
        enemy = new Enemy();
        won = false;
        lost = false;
        playerTurn = true;
        setLayout(null);
        addKeyListener(this);
        fightButton = new JButton("Attack");
        healButton = new JButton("Heal");
        fightButton.addActionListener(this);
        healButton.addActionListener(this);
        fightButton.setBounds(400, 400, 400, 100);
        healButton.setBounds(400, 500, 400, 100);
        fightButton.setFont(new Font("Arial", Font.BOLD, 20));
        healButton.setFont(new Font("Arial", Font.BOLD, 20));
        playerHP = new JLabel(player.getName() + " HP: " + player.getHealth() + "/" + player.getMaxHealth());
        playerHP.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        playerHP.setBounds(475, 290, 310, 55);
        enemyHP = new JLabel(enemy.getName() + " HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        enemyHP.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        enemyHP.setBounds(15, 65, 310, 55);
        statusTXT = new JTextArea("It seems that a " + enemy.getName() + " dared enter the presence of " + player.getName());
        statusTXT.setEditable(false);
        statusTXT.setLineWrap(true);
        statusTXT.setFocusable(false);
        statusTXT.setWrapStyleWord(true);
        statusTXT.setOpaque(false);
        statusTXT.setFont(new Font("Arial", Font.BOLD, 20));
        statusTXT.setForeground(Color.BLACK);
        statusTXT.setBounds(20, 450, 360, 160);
        continueTXT = new JLabel("Press enter to continue");
        continueTXT.setFont(new Font("Impact", Font.PLAIN, 30));
        continueTXT.setForeground(Color.RED);
        continueTXT.setBounds(20, 525, 400, 30);
        continueTXT.setVisible(false);
        add(statusTXT);
        add(continueTXT);
        add(fightButton);
        add(healButton);
        add(playerHP);
        add(enemyHP);
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            BufferedImage bg = ImageIO.read(new File("src\\fight_bg.png"));
            g.drawImage(bg, 0, 0 ,null);
        }   catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.setColor(Color.BLACK);
        g.drawImage(player.getSprite(), 125, 300, null);
        g.drawImage(enemy.getSprite(), 542, 52, null);
        playerHP.setText(player.getName() + " HP: " + player.getHealth() + "/" + player.getMaxHealth());
        enemyHP.setText(enemy.getName() + " HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!lost){
            if (e.getSource() == fightButton && playerTurn) {
                if (player.hasWeapon()) {
                    playerTurn = false;
                    int idx = (int) (Math.random() * (player.getInventory().size()));
                    while (!(player.getInventory().get(idx) instanceof Weapon)) {
                        idx = (int) (Math.random() * player.getInventory().size());
                    }
                    enemy.attacked(((Weapon) player.getInventory().get(idx)).getDamage());
                    statusTXT.setText(player.getName() + " has attacked " + enemy.getName() + " with " + player.getInventory().get(idx).getName() + " for " + ((Weapon) player.getInventory().get(idx)).getDamage() + " DMG!!");
                    checkEnd();
                    continueTXT.setVisible(true);
                } else {
                    statusTXT.setText("You don't have any weapons -_- Guess you're gonna die :)");
                }
            } else if (e.getSource() == healButton && playerTurn) {
                if (player.hasFood()) {
                    System.out.println("B");
                    playerTurn = false;
                    int idx = (int) (Math.random() * (player.getInventory().size()));
                    while (!(player.getInventory().get(idx) instanceof Food)) {
                        idx = (int) (Math.random() * player.getInventory().size());
                    }
                    Food food = (Food) player.getInventory().remove(idx);
                    player.heal(food.getHealAMT());
                    statusTXT.setText(player.getName() + " ate a " + food.getName() + " for " + food.getHealAMT() + " HP!!");
                    checkEnd();
                    continueTXT.setVisible(true);
                } else {
                    statusTXT.setText("You don't have any food. Better survive and rush to McDonalds :/");
                }
            }
        }
        repaint();
        validate();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10 && won) {
            GameWindow.cycleScreen(new MapScreen(player));
        }   else if (e.getKeyCode() == 10 && lost) {
            GameWindow.cycleScreen(new EndScreen(player));
        }   else if (e.getKeyCode() == 10 && !playerTurn) {
            continueTXT.setVisible(false);
            enemyTurn();
            playerTurn = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void enemyTurn() {
        if (!won) {
            boolean fight = Math.random() < 0.5;
            if (fight) {
                int DMG = (int) (Math.random() * 8);
                player.attacked(DMG);
                if (DMG == 0) {
                    statusTXT.setText("Well damn " + enemy.getName() + " missed :/");
                }   else {
                    statusTXT.setText(enemy.getName() + " has attacked " + player.getName() + " for " + DMG + " DMG!!");
                }
            }   else {
                int HP = (int) (Math.random() * 7);
                enemy.heal(HP);
                if (HP == 0) {
                    statusTXT.setText(enemy.getName() + " tried to heal but never learned first aid i guess :/");
                }   else {
                    statusTXT.setText(enemy.getName() + " healed for " + HP + " HP!!");
                }
            }
            checkEnd();
        }
    }

    private void checkEnd() {
        if (player.getHealth() == 0) {
            lost = true;
            System.out.println("ENEMY WON");
            statusTXT.setText(enemy.getName() + " won the battle!");
            continueTXT.setVisible(true);
        }
        if (enemy.getHealth() == 0) {
            won = true;
            player.incrementBattlesWon();
            int coins = (int) (Math.random() * 26) + 10;
            player.setCoins(player.getCoins() + coins);
            statusTXT.setText("You won the Battle and earned " + coins + " coins!!!");
            continueTXT.setVisible(true);
        }

    }
}
