package BrickBreaker;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Canvas 생성 (800 x 600 크기)
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // BrickBreaker.Ball 생성
        Ball ball = new Ball(400, 300, 10, 3, 3, Color.RED);

        // 게임 루프
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // 화면 초기화
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // BrickBreaker.Ball 업데이트 및 그리기
                ball.update();
                ball.checkCollision(canvas.getWidth(), canvas.getHeight());
                ball.draw(gc);
            }
        };
        gameLoop.start();

        // 레이아웃 설정
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Scene 설정
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Brick Breaker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
