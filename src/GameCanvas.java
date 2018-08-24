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

        PlayerBullet b1 = new PlayerBullet(300, 700);

        PlayerBullet b2 = new PlayerBullet(300, 600);

        Enemy e1 = new Enemy(200, 0);

        Enemy e2 = new Enemy(0, 10);

        player = new Player(268, 600);
        player.inputManager = inputManager; // reference, point to

        background = ImageUtil.load("images/background/background.png");

        bullets.add(b1);
        bullets.add(b2);
        enemies.add(e1);
        enemies.add(e2);

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

        if(inputManager.xPressed && !shootLock) {
            PlayerBullet newB = new PlayerBullet(player.x, player.y);
            bullets.add(newB);
            shootLock = true;
        }

        enemySpawnCount++;
        if (enemySpawnCount >= 60) {
            enemySpawnCount = 0;
            Enemy enemy = new Enemy(random.nextInt(600), 10);
            enemies.add(enemy);
        }

        if(shootLock) {
            count++;
            if (count > 15) {
                shootLock = false;
                count = 0;
            }
        }
    }

    int enemySpawnCount = 0;

    boolean shootLock = false;
    int count;

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
