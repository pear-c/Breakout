package BrickBreaker;

import javafx.scene.paint.Color;

public class Brick extends Rectangle {
    // 생성자
    public Brick(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }

    public void reflectBall(Ball ball) {
        if(ball.isCollisionDetected(this)) {
            ball.bounce(this);
        }
    }
}
