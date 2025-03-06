package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Circle implements Drawable {
    private double dx; // 공의 x축 속도 (단위: 픽셀/프레임)
    private double dy; // 공의 y축 속도 (단위: 픽셀/프레임)
    private Color color; // 공의 색상

    // 생성자
    public Ball(double x, double y, double radius, double dx, double dy, Color color) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    // 공을 그리는 메서드
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(getMinX(), getMinY(), radius * 2, radius * 2); // 중심을 기준으로 원 그리기
    }

    // 공의 위치를 업데이트하는 메서드
    public void update() {
        x += dx; // x축 위치 업데이트
        y += dy; // y축 위치 업데이트
    }

    // 공이 벽에 충돌했는지 확인 및 속도 반전
    public void checkCollision(Wall wall) {
        // 좌우 경계 충돌
        if(getMinX() <= wall.getMinX() || getMaxX() >= wall.getMaxX()) {
            dx = -dx; // x축 속도 반전
        }
        // 상하 경계 충돌
        if(getMinY() <= wall.getMinY() || getMaxY() >= wall.getMaxY()) {
            dy = -dy; // y축 속도 반전
        }
    }

    // Getter와 Setter (필요 시 사용)
    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
