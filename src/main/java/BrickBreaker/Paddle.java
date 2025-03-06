package BrickBreaker;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends Rectangle {
    private double speed; // 패들의 이동 속도
    private Color color; // 패들의 색상

    // 생성자
    public Paddle(double x, double y, double width, double height, double speed, Color color) {
        super(x, y, width, height);
        this.speed = speed;
        this.color = color;
    }

    // 패들을 그리는 메서드
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(getMinX(), getMinY(), width, height); // 중심을 기준으로 사각형 그리기
    }

    // 패들의 위치를 왼쪽으로 이동
    public void moveLeft() {
        x -= speed;
    }

    // 패들의 위치를 오른쪽으로 이동
    public void moveRight() {
        x += speed;
    }

    // 패들이 화면 경계를 벗어나지 않도록 제한
    public void checkBounds(double canvasWidth) {
        if (getMinX() < 0) { // 왼쪽 경계
            x = width / 2;
        } else if (getMaxX() > canvasWidth) { // 오른쪽 경계
            x = canvasWidth - width / 2;
        }
    }

    // 공과 충돌 여부 확인
    public boolean checkCollision(Ball ball) {
        return ball.getMaxY() >= getMinY() &&
                ball.getMinY() < getMinY() &&
                ball.getMaxX() > getMinX() &&
                ball.getMinX() < getMaxX();
    }
}
