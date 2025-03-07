package BrickBreaker;

import javafx.scene.paint.Color;

import java.util.List;

// TODO - Step10 : 이동하는 객체를 Movable 한 객체로 분류 및 오버라이딩
// TODO - Step11 : Bounceable 성질 표현
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

    // TODO - Step11 : Bounceable 성질 표현
    @Override
    public void bounce(Shape other) {
        boolean collisionX = (getMaxX() >= other.getMinX() && getMinX() <= other.getMaxX());
        boolean collisionY = (getMinY() <= other.getMaxY() && getMaxY() >= other.getMinY());

        // 벽과의 충돌 계산은 조금 다름. (Wall 클래스 설계 참고)
        if(other instanceof Wall) {
            boolean collisionWithWallX = (getMinX() <= other.getMinX() || getMaxX() >= other.getMaxX());
            boolean collisionWithWallY = (getMinY() <= other.getMinY() || getMaxY() >= other.getMaxY());

            if(collisionWithWallX && collisionWithWallY) {
                setDx(-dx);
                setDy(-dy);
            }
            else if(collisionWithWallX) {
                setDx(-dx);
            }
            else if(collisionWithWallY) {
                setDy(-dy);
            }
        }
        else {
            // 꼭짓점에 부딪혔을 경우
            if(collisionX && collisionY) {
                setDx(-dx);
                setDy(-dy);
            }
            // 옆면과 부딪혔을 경우
            else if(collisionX) {
                setDx(-dx);
            }
            // 윗쪽, 아랫쪽과 부딪혔을 경우
            else if(collisionY) {
                setDy(-dy);
            }
        }
    }
}
