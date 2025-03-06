package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {
    protected double x, y;    // 중심 좌표

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public abstract double getMinX();
    public abstract double getMaxX();
    public abstract double getMinY();
    public abstract double getMaxY();
}
