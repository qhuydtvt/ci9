import java.awt.*;
import java.util.ArrayList;

public class Player {
    Vector2D position;

    Image image;
    InputManager inputManager;
    boolean shootLock;
    ArrayList<PlayerBullet> bullets;

    public Player(int x, int y) {
        this.position = new Vector2D(x, y);
        this.image = ImageUtil.load("images/player/MB-69/player1.png");
    }

    void render(Graphics g) {
        g.drawImage(this.image, (int)this.position.x, (int)this.position.y, null);
    }

    void run() {
        this.move();
        this.shoot();
    }

    private void shoot() {
        if(this.inputManager.xPressed && !this.shootLock) {
            PlayerBullet newBullet = new PlayerBullet((int)this.position.x, (int)this.position.y);
            this.bullets.add(newBullet);
            this.shootLock = true;
        }
    }

    void move() {
        Vector2D velocity = new Vector2D();

        if(inputManager.rightPressed) {
            velocity.x += 5;
        }
        if(inputManager.leftPressed) {
            velocity.x -= 5;
        }
        if(inputManager.downPressed) {
            velocity.y += 5;
        }
        if(inputManager.upPressed) {
            velocity.y -= 5;
        }

        this.position.addUp(velocity);
    }
}
