import javax.swing.*;
import screens.GameWindow;
import screens.StartScreen;


public class Main {
    public static void main(String[] args) {
        GameWindow window = new GameWindow(new StartScreen());
    }
}
