package players;


import bases.GameObject;
import bases.ImageRenderer;

public class Player extends GameObject {
    private PlayerMove playerMove;
    private PlayerShoot playerShoot;

    public Player(int x, int y) {
        super(x, y);
        this.renderer = new ImageRenderer("images/player/MB-69/player1.png");
        this.playerMove = new PlayerMove();
        this.playerShoot = new PlayerShoot();
    }

    public void run() {
        super.run();
        this.move(); // move
        this.shoot(); // shoot
    }

    private void shoot() {
        this.playerShoot.run(this);
    }

    void move() {
        this.playerMove.run(this.position);
    }
}
