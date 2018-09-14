package bases;

import players.PlayerBullet;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    public Vector2D position;
    public Renderer renderer;
    public boolean isActive;

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();
    private static ArrayList<GameObject> newGameObjects = new ArrayList<>();

    public static void add(GameObject g) {
        newGameObjects.add(g);
    }

    public static void runAll() {
        for(GameObject go: gameObjects) {
            if (go.isActive)
                go.run();
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
        System.out.println(gameObjects.size());
    }

    public static void renderAll(Graphics g) {
        for(GameObject go: gameObjects) {
            if(go.isActive)
                go.render(g);
        }
    }

    public static PlayerBullet recycle(int x, int y) {
        PlayerBullet pb = null;

        for(GameObject go: gameObjects) {
            if(!go.isActive) {
                if (go instanceof PlayerBullet) {
                    pb = (PlayerBullet) go;
                }
            }
        }

        if (pb == null) {
            pb = new PlayerBullet(x, y);
            GameObject.add(pb);
        }
        else {
            pb.isActive = true;
            pb.position.x = x;
            pb.position.y = y;
        }

        return pb;
    }

    // Generics
    public static <T extends GameObject> T checkCollision(BoxCollider boxCollider, Class<T> cls) {

        T result = null;

        for(GameObject go : gameObjects) {
            if(go.isActive && go.boxCollider != null) {
                if(go.getClass().equals(cls)) {
                    if(go.boxCollider.collideWith(boxCollider)) {
                        result = (T)go;
                    }
                }
            }
        }

        return result;
    }

    public BoxCollider boxCollider;

    public GameObject(int x, int y) {
        this.position = new Vector2D(x, y);
        this.renderer = null; // not yet specified
        this.boxCollider = null;
        this.isActive = true;
    }

    public void run() {
        if(this.boxCollider != null) {
            this.boxCollider.position.x = this.position.x;
            this.boxCollider.position.y = this.position.y;
            this.boxCollider.run();
        }
    }

    public void render(Graphics g) {
        if (this.renderer != null) {
            this.renderer.render(g, this.position);
        }

        if(this.boxCollider != null) {
            this.boxCollider.render(g);
        }
    }

    public void destroy() {
        this.isActive = false;
    }
}
