package BrickBreaker;

import javafx.scene.paint.Color;

import java.util.List;

// TODO - Step10 : 이동하는 객체를 Movable 한 객체로 분류 및 오버라이딩
public class Ball extends Circle implements Movable, Bounceable{
    private double dx; // 공의 x축 속도 (단위: 픽셀/프레임)
    private double dy; // 공의 y축 속도 (단위: 픽셀/프레임)
    private boolean isPaused;   // 이동 가능 or 불가능

    // 생성자
    public Ball(double x, double y, double radius, double dx, double dy, Color color) {
        super(x, y, radius, color);
        this.dx = dx;
        this.dy = dy;
        this.isPaused = false;
    }

    // 위치 보정하는 메서드 (충돌 발생 시 실행)
    public void resolveCollision(Shape other) {
        // 벽과 충돌 했을 시에는 x축, y축 속도 각각 반전
        if(other instanceof Wall wall) {
            // 좌우 경계 충돌
            if(getMinX() <= wall.getMinX() || getMaxX() >= wall.getMaxX()) {
                dx = -dx; // x축 속도 반전
            }
            // 상하 경계 충돌
            if(getMinY() <= wall.getMinY() || getMaxY() >= wall.getMaxY()) {
                dy = -dy; // y축 속도 반전
            }
        }
    }

    @Override
    public void move() {
        if(!isPaused) {
            x += dx;
            y += dy;
        }
    }

    @Override
    public double getDx() {
        return dx;
    }

    @Override
    public double getDy() {
        return dy;
    }

    @Override
    public void setDx(double dx) {
        this.dx = dx;
    }

    @Override
    public void setDy(double dy) {
        this.dy = dy;
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    // 충돌 감지 메서드
    @Override
    public boolean isCollisionDetected(Shape other) {
        return getMaxX() >= other.getMinX() &&
                getMinX() <= other.getMaxX() &&
                getMaxY() >= other.getMinY() &&
                getMinY() <= other.getMaxY();
    }

    @Override
    public void bounce(Shape other) {
        // 옆면과 부딪힐 때
        if(getMaxX() >= other.getMinX() && getMinX() <= other.getMaxX()) {
            setDx(-dx);
        }
        // 윗쪽, 아랫쪽과 부딪힐 때
        else if(getMinY() <= other.getMaxY() && getMaxY() >= other.getMinY()) {
            setDy(-dy);
        }
        // 꼭짓점에 부딪힐 때
        else if(getMaxX() >= other.getMinX() && getMinX() <= other.getMaxX() &&
                getMinY() <= other.getMaxY() && getMaxY() >= other.getMinY()) {
            setDx(-dx);
            setDy(-dy);
        }
    }
}
