import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameWindow window = new GameWindow(new StartScreen());
        //GameWindow window = new GameWindow(new EndScreen(new Player(0,0,"")));
    }
}
