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

    // 공이 벽에 닿으면 튕겨내는 메서드
    public void reflectBall(Ball ball) {
        if(ball.isCollisionDetected(this)) {
            ball.bounce(this);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setLineWidth(3);
        gc.strokeRect(getMinX(), getMinY(), width, height);
    }
}
