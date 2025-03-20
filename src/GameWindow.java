import javax.swing.*;

public class GameWindow {
    public GameWindow(JPanel screen) {
        JFrame window = new JFrame("Sameer's Fight Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        //window.setVisible(true);


        window.add(screen);
        window.setVisible(true);
    }
}
