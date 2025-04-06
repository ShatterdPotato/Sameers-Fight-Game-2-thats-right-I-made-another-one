import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShopScreen extends JPanel implements ActionListener {
    private Shop shop;
    private Player player;
    private JTextArea cashierText;
    private JTextArea shopMenu;
    private JTextField textBox;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel numCoins;
    private int lastPrice;


    public ShopScreen(Player player) {
        this.player = player;
        this.shop = new Shop(player);

        cashierText = new JTextArea("welcome to macdonl what need");
        cashierText.setEditable(false);
        cashierText.setLineWrap(true);
        cashierText.setOpaque(false);
        cashierText.setFocusable(false);
        cashierText.setWrapStyleWord(true);
        cashierText.setBounds(402, 34, 223, 67);
        cashierText.setLocation(402, 34);

        confirmButton = new JButton("Yeah");
        confirmButton.setVisible(false);
        confirmButton.addActionListener(this);

        cancelButton = new JButton("Nah");
        cancelButton.setVisible(false);
        cancelButton.addActionListener(this);

        textBox = new JTextField(15);
        textBox.addActionListener(this);

        numCoins = new JLabel(String.valueOf(player.getCoins()));
        numCoins.setFont(new Font("Arial", Font.BOLD, 40));

        add(textBox);
        add(numCoins);
        createMenu();
    }

    public void paintComponent(Graphics g) {
        try {
            BufferedImage bg = ImageIO.read(new File("src\\shop_bg.png"));
            g.drawImage(bg, 0, 0 ,null);
        }   catch (IOException e) {
            System.out.println(e.getMessage());
        }
        cashierText.setLocation(402, 34);
        textBox.setLocation(239,283);
        confirmButton.setLocation(238, 297);
        cancelButton.setLocation(338, 297);
        numCoins.setLocation(75, 10);
        numCoins.setText(String.valueOf(player.getCoins()));
        add(cashierText);
        add(textBox);
        add(confirmButton);
        add(cancelButton);
        add(numCoins);
        displayWallet(g);
        shopMenu.setBounds(460, 275, 320, 310);
        shopMenu.setLocation(460,275);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textBox) {
            lastPrice = shop.fetchPrice(textBox.getText().toLowerCase());
            if (lastPrice == -1) {
                cashierText.setText("Bro you already bought that greedy ahh");
            }   else if (lastPrice == -2) {
                cashierText.setText(Misc.extraneousResponse());
            }   else {
                textBox.setVisible(false);
                remove(textBox);
                confirmButton.setVisible(true);
                cancelButton.setVisible(true);
                cashierText.setText("Ok that'll be " + lastPrice + " coins. Buy?");
            }
        }   else if (e.getSource() == confirmButton) {
            cancelButton.setVisible(false);
            if (confirmButton.getText().equals("Yeah")) {
                if (player.getCoins() >= lastPrice) {
                    player.setCoins(player.getCoins() - lastPrice);
                    shop.buyItem(lastPrice);
                    cashierText.setText("Ok there you go, one " + player.getInventory().getLast().getName());
                    confirmButton.setText(":)");
                } else {
                    cashierText.setText("BAHAHAHA yo broke ahh cant even afford stuff from McDonalds. GET OUT!");
                    confirmButton.setVisible(true);
                    confirmButton.setText(":(");
                }
            }   else if (confirmButton.getText().equals(":(") || confirmButton.getText().equals(":)")) {
                GameWindow.cycleScreen(new MapScreen(player));
            }
        } else if (e.getSource() == cancelButton) {
            cancelButton.setVisible(false);
            cashierText.setText("Ok? Why tf did you ask then? GET OUT!");
            confirmButton.setVisible(true);
            confirmButton.setText(":(");
        }
        repaint();
        validate();
    }

    private void displayWallet(Graphics g) {
        try {
            BufferedImage coin = ImageIO.read(new File("src\\coin.png"));
            g.drawImage(coin, 0, 0, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void createMenu() {
        shopMenu = new JTextArea();
        shopMenu.setEditable(false);
        shopMenu.setLineWrap(true);
        shopMenu.setFocusable(false);
        shopMenu.setWrapStyleWord(true);
        shopMenu.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        shopMenu.setText(
                "                      MENU                    \n" +
                "1. Food (Random)......................Cost:5 \n" +
                "2. Wooden Sword..............DMG: 3,  Cost:10\n" +
                "3. Alarm Clock...............DMG: 5,  Cost:20\n" +
                "4. Gold Scar.................DMG: 7,  Cost:35\n" +
                "5. College Rejection Letter..DMG: 5,  Cost:45\n" +
                "6. Dark Magic for Dummies....DMG: 10, Cost:65\n" +
                "7. Legendary Zenith..........DMG: 15, Cost:65\n"
        );
        shopMenu.setBounds(460, 275, 320, 310);
        shopMenu.setLocation(460,275);
        add(shopMenu);
    }
}
