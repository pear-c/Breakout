package BrickBreaker;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private boolean gameFinished = false; // 게임 완료 여부를 나타내는 플래그

    private boolean moveLeft = false;
    private boolean moveRight = false;

    @Override
    public void start(Stage primaryStage) {
        // Canvas 생성 (800 x 600 크기)
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Ball, Paddle, Wall, 벽돌 생성
        Ball ball = new Ball(400, 300, 10, 3, 3, Color.RED);
        Paddle paddle = new Paddle(400, 550, 100, 20, 5, Color.BLUE);
        Wall wall = new Wall(400, 300, 780, 580, Color.SANDYBROWN);
        List<Brick> bricks = createBricks(5, 10, 70, 20, 5, 65, 50, Color.BLUE);

        // TODO - Step09 : List<Shape> 로 통합 관리
        List<Shape> shapes = new ArrayList<>();
        shapes.add(ball);
        shapes.add(paddle);
        shapes.add(wall);

        // 게임 루프
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // 게임이 종료되었으면 루프 중단
                if(gameFinished) {
                    return;
                }

                // 화면 초기화
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // TODO - Step09 :Drawable로 선언된 객체만 그리도록 수정
                // 도형들 출력
                for(Shape shape : shapes) {
                    if(shape instanceof Drawable) {
                        ((Drawable) shape).draw(gc);
                    }
                }

                // Ball 업데이트 및 충돌 확인
                ball.update();
                ball.checkCollision(wall);
                // TODO - Step07 : 아직 블럭이 남아있는데 땅에 닿으면 게임 오버
                if(wall.downCollision(ball) && !isAllBricksCleared(bricks)) {
                    gameFinished = true;
                    showGameOverPopup();
                }

                // 벽돌 그리기 및 충돌 처리
                for(Brick brick : bricks) {
                    if(brick.checkCollision(ball)) {
                        ball.setDy(-ball.getDy()); // 충돌 시 공의 y 방향 반전
                    }
                    if(!brick.isDestroyed()) // 부숴지지 않은 벽돌 출력
                        brick.draw(gc);
                }

                // Paddle 움직임 처리
                if(moveLeft) {
                    paddle.moveLeft();
                }
                if(moveRight) {
                    paddle.moveRight();
                }
                paddle.checkBounds(canvas.getWidth());

                // TODO - Step07 : 패들과 부딪히면 공 튀겨냄
                if(paddle.checkCollision(ball)) {
                    ball.setDy(-ball.getDy()); // 충돌 시 공의 y 방향 반전
                }
            }
        };
        gameLoop.start();

        // 키보드 입력 처리
        Scene scene = new Scene(new StackPane(canvas), 800, 600);
        keyboardEventsHandler(scene);

        // Stage 설정
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 벽돌 생성하는 메서드
    private List<Brick> createBricks(int rows, int cols, double brickWidth, double brickHeight,
                                     double padding, double startX, double startY, Color color) {
        List<Brick> bricks = new ArrayList<>();

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                double x = startX + col * (brickWidth + padding);
                double y = startY + row * (brickHeight + padding);
                bricks.add(new Brick(x, y, brickWidth, brickHeight, color));
            }
        }
        return bricks;
    }

    // 키보드 핸들러 익명 함수 처리 메서드
    private void keyboardEventsHandler(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.LEFT) {
                moveLeft = true;
            } else if(event.getCode() == KeyCode.RIGHT) {
                moveRight = true;
            }
        });
        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.LEFT) {
                moveLeft = false;
            } else if(event.getCode() == KeyCode.RIGHT) {
                moveRight = false;
            }
        });
    }

    // TODO - Chap07 : 클리어 조건 확인 메서드 (모든 블럭 제거)
    private boolean isAllBricksCleared(List<Brick> bricks) {
        for(Brick brick : bricks) {
            if(!brick.isDestroyed())
                return false;
        }
        return true;
    }

    // 팝업을 표시하고 종료하는 메서드
    private void showGameOverPopup() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game Over! Thank you for playing.", ButtonType.OK);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);

            // 팝업 닫기 후 게임 종료
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK) {
                    Platform.exit(); // 게임 종료
                }
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
