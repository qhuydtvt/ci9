package game;

import bases.GameObject;
import bases.ImageUtil;
import enemies.Enemy;
import enemies.EnemySpawner;
import players.Player;
import players.PlayerBullet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends JPanel {

    Image background; // null

    Player player;

    BufferedImage backBuffer; // null
    Graphics backbufferGraphics;

    Random random;

    public GameCanvas() {
        random = new Random();

        player = new Player(300, 700);

        GameObject.add(player);
        GameObject.add(new EnemySpawner());

        background = ImageUtil.load("images/background/background.png");

        backBuffer = new BufferedImage(600, 800, BufferedImage.TYPE_INT_ARGB);
        backbufferGraphics = backBuffer.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backBuffer, 0, 0, null);
    }

    void run() {
        GameObject.runAll();
    }


    void render() {
        backbufferGraphics.drawImage(background, 0, 0, null);

        GameObject.renderAll(backbufferGraphics);

        this.repaint();
    }
}
