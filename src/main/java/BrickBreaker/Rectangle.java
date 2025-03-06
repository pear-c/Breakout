package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// 사각형 클래스 -> 그릴 수 있는 형태
public class Rectangle extends Shape implements Drawable {
    protected double width;  // 가로 길이
    protected double height; // 세로 길이
    protected Color color;   // 색상

    public Rectangle(double x, double y, double width, double height, Color color) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(getMinX(), getMinY(), width, height);
    }

    @Override
    public double getMinX() {
        return x - width / 2;
    }

    @Override
    public double getMaxX() {
        return x + width / 2;
    }

    @Override
    public double getMinY() {
        return y - height / 2;
    }

    @Override
    public double getMaxY() {
        return y + height / 2;
    }
}
