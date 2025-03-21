import javax.swing.*;

public class GameWindow {
    private static JPanel currScreen;
    private static JFrame window;
    public GameWindow(JPanel screen) {
        currScreen = screen;
        window = new JFrame("Sameer's Fight Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);

        window.add(currScreen);
        window.setVisible(true);
    }

    public static JPanel getCurrScreen() {
        return getCurrScreen();
    }

    public static void cycleScreen(JPanel newScreen) {
        currScreen = newScreen;
        window.getContentPane().removeAll();
        window.add(currScreen);
    }
}
