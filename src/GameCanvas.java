import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends JPanel {

    Image background; // null

    ArrayList<PlayerBullet> bullets;
    ArrayList<Enemy> enemies;
    Player player;

    InputManager inputManager;

    BufferedImage backBuffer; // null
    Graphics backbufferGraphics;

    Random random;

    public GameCanvas() {
        random = new Random();

        inputManager = new InputManager();

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();

        player = new Player(268, 600);
        player.bullets = this.bullets;
        player.inputManager = inputManager; // reference, point to

        background = ImageUtil.load("images/background/background.png");

        backBuffer = new BufferedImage(600, 800, BufferedImage.TYPE_INT_ARGB);
        backbufferGraphics = backBuffer.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backBuffer, 0, 0, null);
    }

    void run() {
        player.run();

        for(PlayerBullet b: bullets) {
            b.run();
        }

        for (Enemy e: enemies) {
            e.run();
        }

        enemySpawnCount++;
        if (enemySpawnCount >= 60) {
            enemySpawnCount = 0;
            Enemy enemy = new Enemy(random.nextInt(600), 10);
            enemies.add(enemy);
        }
    }

    int enemySpawnCount = 0;

    void render() {
        backbufferGraphics.drawImage(background, 0, 0, null);
        player.render(backbufferGraphics);

        for(PlayerBullet b: bullets) {
            b.render(backbufferGraphics);
        }

        for (Enemy e: enemies) {
            e.render(backbufferGraphics);
        }

        this.repaint();
    }
}
