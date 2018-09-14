package bases;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D(float x, float y) { // 2 doi so :  2 parameters
        this.x = x;
        this.y = y;
    }

    public Vector2D() { // khong doi so: no parameters (takes no parameters)
        this(0, 0);
    }

    void print() {
        System.out.println(this.x + ", " + this.y);
    }

    public void addUp(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void addUp(Vector2D other) {
        this.addUp(other.x, other.y);
    }

    Vector2D add(float x, float y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    Vector2D add(Vector2D other) {
        return this.add(other.x, other.y);
    }

    void subtractBy(float x, float y) {
        this.x -= x;
        this.y -= y;
    }

    void subtractBy(Vector2D other) {
        this.subtractBy(other.x, other.y);
    }

    public Vector2D subtract(float x, float y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    Vector2D subtract(Vector2D other) {
        return this.subtract(other.x, other.y);
    }

    void scaleBy(float f) {
        this.x *= f;
        this.y *= f;
    }

    Vector2D scale(float f) {
        return new Vector2D(this.x * f, this.y * f);
    }

    float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    Vector2D normalize() {
        float length = this.length();
        return new Vector2D(this.x / length, this.y / length);
    }

    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(2, 3);
        assert v1.x == 2 && v1.y == 3;

        Vector2D v3 = v1.add(3, 4);
        assert v3.x == 5 && v3.y == 7;

        Vector2D v4 = new Vector2D(2, 4);
        v4.subtractBy(1, 2);
        assert v4.x == 1;
        assert v4.y == 2;

        v4.subtractBy(new Vector2D(1, 3));
        assert v4.x == 0;
        assert v4.y == -1;

        Vector2D v5 = v4.subtract(4, 3);
        assert v5.x == -4;
        assert v5.y == -4;
        assert v4.x == 0;
        assert v4.y == -1;
    }
}