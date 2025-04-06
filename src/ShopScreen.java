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
    private JTextField textBox;
    private JButton confirmButton;
    private JButton cancelButton;
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
        cancelButton = new JButton("Nah");
        confirmButton.setVisible(false);
        cancelButton.setVisible(false);
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        textBox = new JTextField(15);
        textBox.addActionListener(this);
        add(cashierText);
        add(textBox);
        add(confirmButton);
        add(cancelButton);
    }

    public void paintComponent(Graphics g) {
        try {
            BufferedImage bg = ImageIO.read(new File("src\\shop_bg.png"));
            g.drawImage(bg, 0, 0 ,null);
        }   catch (IOException e) {
            System.out.println(e.getMessage());
        }
        add(cashierText);
        add(textBox);
        add(confirmButton);
        add(cancelButton);
        cashierText.setLocation(402, 34);
        textBox.setLocation(239,283);
        confirmButton.setLocation(258, 297);
        cancelButton.setLocation(282, 297);
        textBox.setFocusable(true);
        displayWallet(g);
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

                confirmButton.setVisible(true);
                cancelButton.setVisible(false);
                cashierText.setText("Ok that'll be " + lastPrice + " coins. Buy?");
            }
        }   else if (e.getSource() == confirmButton) {
            cancelButton.setVisible(false);
            if (confirmButton.getText().equals("Yeah")) {
                if (player.getCoins() >= lastPrice) {
                    player.setCoins(player.getCoins() - lastPrice);
                    shop.buyItem(lastPrice);
                } else {
                    cashierText.setText("BAHAHAHA yo broke ahh cant even afford stuff from McDonalds. GET OUT!");
                    confirmButton.setVisible(true);
                    confirmButton.setText(":(");
                }
            }   else if (confirmButton.getText().equals(":(")) {
                GameWindow.cycleScreen(new MapScreen(player));
            }
        } else if (e.getSource() == cancelButton) {
            cancelButton.setVisible(false);
            cashierText.setText("Ok then? Why tf did you ask then? GET OUT!");
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
        g.drawString(": " + player.getCoins(), 64, 0);
    }
}
