import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JPanel screen = new MapScreen(new Map());
        GameWindow window = new GameWindow(screen);
    }
}
