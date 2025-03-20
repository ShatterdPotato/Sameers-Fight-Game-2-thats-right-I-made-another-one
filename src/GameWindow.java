import javax.swing.JFrame;

public class GameWindow {
    public GameWindow() {
        JFrame window = new JFrame("Sameer's Fight Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        SameerFightGameLogic gameLogicPanel = new SameerFightGameLogic();
        window.add(gameLogicPanel);
        window.setVisible(true);
    }
}
