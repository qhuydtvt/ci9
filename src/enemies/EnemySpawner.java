package enemies;

import bases.FrameCounter;
import bases.GameObject;

import java.util.Random;

public class EnemySpawner extends GameObject {
    private FrameCounter frameCounter;
    private Random random;

    public EnemySpawner() {
        super(0, 0);
        this.frameCounter = new FrameCounter(100);
        this.random = new Random();
    }

    @Override
    public void run() {
        super.run();
        frameCounter.run();
        if (frameCounter.expired) {
            frameCounter.reset();
            Enemy newEnemy = new Enemy(
                    random.nextInt(600),
                    0
            );

            GameObject.add(newEnemy);
        }
    }
}
