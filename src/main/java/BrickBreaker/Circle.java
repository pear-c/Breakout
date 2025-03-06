package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// 원형 클래스 -> 그릴 수 있는 형태
public class Circle extends Shape implements Drawable {
    protected double radius; // 반지름
    protected Color color; // 색상

    public Circle(double x, double y, double radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(getMinX(), getMinY(), radius * 2, radius * 2); // 중심을 기준으로 원 그리기
    }

    @Override
    public double getMinX() {
        return x - radius;
    }

    @Override
    public double getMaxX() {
        return x + radius;
    }

    @Override
    public double getMinY() {
        return y - radius;
    }

    @Override
    public double getMaxY() {
        return y + radius;
    }
}
