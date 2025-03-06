package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Rectangle {
    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    // 게임 장소 : 화면 내 -> Wall 안 으로 변경 (Wall 경계에 닿으면 튕기거나 게임 종료)
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.strokeRect(getMinX(), getMinY(), width, height);
    }

    // 공과 충돌 여부 확인
    public boolean checkCollision(Ball ball) {
        return ball.getMaxX() >= getMinX() &&
                ball.getMinX() <= getMaxX() &&
                ball.getMaxY() >= getMinY() &&
                ball.getMinY() <= getMaxY();
    }
}
