package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Rectangle implements Drawable {
    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    // 게임 장소 : 화면 내 -> Wall 안 으로 변경 (Wall 경계에 닿으면 튕기거나 게임 종료)
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(3);
        gc.strokeRect(getMinX(), getMinY(), width, height);
    }

    // TODO - Step07 : 공이 땅에 닿았는지 확인용 메서드
    public boolean downCollision(Ball ball) {
        return ball.getMaxY() >= getMaxY();
    }
}
