package BrickBreaker;

import javafx.scene.paint.Color;

// TODO - Step10 : 이동하는 객체를 Movable 한 객체로 분류 및 오버라이딩
public class Paddle extends Rectangle implements Movable {
    private double speed; // 패들의 이동 속도
    private boolean isPaused; // 이동 가능 or 불가능

    // 생성자
    public Paddle(double x, double y, double width, double height, double speed, Color color) {
        super(x, y, width, height, color);
        this.speed = speed;
        this.isPaused = false;
    }

    // 패들의 위치를 왼쪽으로 이동
    public void moveLeft() {
        x -= speed;
    }

    // 패들의 위치를 오른쪽으로 이동
    public void moveRight() {
        x += speed;
    }

    // 패들이 Wall 안쪽을 벗어나지 않도록 설정
    public void checkBounds(Wall wall) {
        if (getMinX() < wall.getMinX()) { // 왼쪽 경계
            x = wall.getMinX() + width / 2 ;
        } else if (getMaxX() > wall.getMaxX()) { // 오른쪽 경계
            x = wall.getMaxX() - width / 2;
        }
    }

    // 공이 패들에 닿으면 튕겨내는 메서드
    public void reflectBall(Ball ball) {
        if(ball.isCollisionDetected(this)) {
            ball.bounce(this);
        }
    }


    @Override
    public void move() {
        if(!isPaused) {
            x += speed;
        }

    }

    @Override
    public double getDx() {
        return speed;
    }

    @Override
    public double getDy() {
        return 0;
    }

    @Override
    public void setDx(double dx) {
        speed = dx;
    }

    @Override
    public void setDy(double dy) {}

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    @Override
    public boolean isCollisionDetected(Shape other) {
        return getMaxX() >= other.getMinX() &&
                getMinX() <= other.getMaxX() &&
                getMaxY() >= other.getMinY() &&
                getMinY() <= other.getMaxY();
    }
}
