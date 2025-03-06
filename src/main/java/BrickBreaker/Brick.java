package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends Rectangle {
    private boolean isDestroyed; // 벽돌이 파괴되었는지 여부

    // 생성자
    public Brick(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
        this.isDestroyed = false; // 초기 상태는 파괴되지 않음
    }

    // 공과 충돌 여부 확인
    public boolean checkCollision(Ball ball) {
        if (isDestroyed) {
            return false; // 이미 파괴된 벽돌은 충돌하지 않음
        }

        // 공이 벽돌의 경계와 충돌했는지 확인
        boolean collision = ball.getMaxX() > getMinX() &&
                            ball.getMinX() < getMaxX() &&
                            ball.getMaxY() > getMinY() &&
                            ball.getMinY() < getMaxY();

        if (collision) {
            isDestroyed = true; // 벽돌 파괴
        }

        return collision;
    }

    // Getter와 Setter (필요 시 사용)
    public boolean isDestroyed() {
        return isDestroyed;
    }
}
