package BrickBreaker;

public class Rectangle extends Shape {
    protected double width;
    protected double height;

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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
