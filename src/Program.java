import bases.GameObject;
import enemies.Enemy;
import game.GameWindow;
import players.Player;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.mainLoop();
    }
}
