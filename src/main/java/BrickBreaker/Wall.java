package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Rectangle {
    public Wall(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }

    // TODO - Step07 : 공이 땅에 닿았는지 확인용 메서드
    public boolean downCollision(Ball ball) {
        return ball.getMaxY() >= getMaxY();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setLineWidth(3);
        gc.strokeRect(getMinX(), getMinY(), width, height);
    }
}
