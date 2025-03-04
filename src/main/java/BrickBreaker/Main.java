package BrickBreaker;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    private boolean moveLeft = false;
    private boolean moveRight = false;

    @Override
    public void start(Stage primaryStage) {
        // Canvas 생성 (800 x 600 크기)
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Ball과 Paddle 생성
        Ball ball = new Ball(400, 300, 10, 3, 3, Color.RED);
        Paddle paddle = new Paddle(400, 550, 100, 20, 5, Color.BLUE);

        // 게임 루프
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // 화면 초기화
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // Ball 업데이트 및 그리기
                ball.update();
                ball.checkCollision(canvas.getWidth(), canvas.getHeight());
                ball.draw(gc);

                // Paddle 움직임 처리
                if(moveLeft) {
                    paddle.moveLeft();
                }
                if(moveRight) {
                    paddle.moveRight();
                }

                // Paddle 경계 확인 및 그리기
                paddle.checkBounds(canvas.getWidth());
                paddle.draw(gc);
            }
        };
        gameLoop.start();

        // 키보드 입력 처리
        Scene scene = new Scene(new StackPane(canvas), 800, 600);
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

        // Stage 설정
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
