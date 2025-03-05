package BrickBreaker;

public class Circle extends Shape {
    protected double radius; // 반지름

    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
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
