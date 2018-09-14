package enemies;

import bases.*;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends GameObject {

    public Enemy(int x, int y) {
        super(x, y);

        this.renderer = new Animation(
                ImageUtil.load("images/enemy/bacteria/bacteria1.png"),
                ImageUtil.load("images/enemy/bacteria/bacteria2.png"),
                ImageUtil.load("images/enemy/bacteria/bacteria3.png")
        );

        this.boxCollider = new BoxCollider(x, y, 30, 30);
    }

    public void run() {
        super.run();
        this.position.addUp(0, 3);
    }

    public void getHit() {
        this.destroy();
        EnemyExplosion explosion = new EnemyExplosion(
                (int)this.position.x,
                (int)this.position.y
        );
        GameObject.add(explosion);
    }
}
