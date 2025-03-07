package BrickBreaker;

// TODO - Step10 : 이동 가능한 객체 분류
public interface Movable {
    void move();
    double getDx();
    double getDy();
    void setDx(double dx);
    void setDy(double dy);
    void pause();
    void resume();
    boolean isCollisionDetected(Shape other);
}
