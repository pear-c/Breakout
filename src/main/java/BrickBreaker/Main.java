package BrickBreaker;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private boolean gameFinished = false; // 게임 완료 여부를 나타내는 플래그
    private boolean moveLeft = false;   // 키보드 입력 여부 : 왼쪽 화살표
    private boolean moveRight = false;  // 키보드 입력 여부 : 오른쪽 화살표

    private int score = 0; // 점수 표시할 변수

    private Ball ball;
    private Paddle paddle;
    private Wall wall;
    private List<Brick> bricks;
    private List<Brick> toRemoveBricks; // 삭제할 벽돌 임시 저장용 리스트
    private List<Shape> shapes;

    private Canvas canvas;
    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        // 초기 설정 : Canvas, GraphicContext, Objects
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        initGameObjects();

        // 게임 시작
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

                // TODO - Step09 :Drawable로 선언된 객체들만 출력
                drawShapes();

                // 충돌 처리 및 객체들 상태 업데이트
                updateStates();

                // 게임 종료 조건 만족 여부 확인
                checkGameOver();
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

    // 오브젝트 설정 메서드
    private void initGameObjects() {
        ball = new Ball(400, 300, 10, 3, 3, Color.RED);
        paddle = new Paddle(400, 550, 100, 20, 5, Color.BLUE);
        wall = new Wall(400, 300, 780, 580, Color.SANDYBROWN);
        bricks = createBricks(5, 10, 70, 20, 5, 65, 50, Color.BLUE);
        toRemoveBricks = new ArrayList<>(); // 삭제할 벽돌 용 리스트

        // TODO - Step09 : List<Shape> 로 통합 관리
        shapes = new ArrayList<>();
        shapes.add(ball);
        shapes.add(paddle);
        shapes.add(wall);
        shapes.addAll(bricks);
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

    // 도형들 출력 메서드
    private void drawShapes() {
        for(Shape shape : shapes) {
            if(shape instanceof Drawable) {
                ((Drawable) shape).draw(gc);
            }
        }
        // 점수 출력
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(20));
        gc.fillText("Score: " + score, 13, 30);
    }

    // 충돌 처리 및 상태 업데이트 메서드
    private void updateStates() {
        // Ball 움직이기 및 충돌 확인
        ball.move();
        if(ball.isCollisionDetected(wall)) {
            wall.reflectBall(ball);
        }


        // TODO - Step12 : 깨진 벽돌 객체 삭제
        // 벽돌 충돌 처리 -> 깨진 벽돌 삭제
        for(Brick brick : bricks) {
            if(ball.isCollisionDetected(brick)) {
                brick.reflectBall(ball);
                toRemoveBricks.add(brick);
                score += 100; // 벽돌 당 100점 추가
            }
        }
        bricks.removeAll(toRemoveBricks); // 벽돌 리스트에서 삭제
        shapes.removeAll(toRemoveBricks); // 전체 객체 상태에도 반영
        toRemoveBricks.clear();


        // Paddle 움직임 처리 및 충돌 확인
        if(moveLeft) {
            paddle.moveLeft();
        }
        if(moveRight) {
            paddle.moveRight();
        }
        paddle.checkBounds(wall);
        // TODO - Step07 : 패들과 부딪히면 공 튕겨냄
        if(ball.isCollisionDetected(paddle)) {
            paddle.reflectBall(ball);
        }
    }

    // 게임 종료 조건 확인 메서드
    private void checkGameOver() {
        // TODO - Step07 : 게임 종료 조건 (블록 모두 클리어 or 그 전에 공에 바닥에 닿으면 게임 오버)
        if(wall.downCollision(ball) && !isAllBricksCleared(bricks)) {
            gameFinished = true;
            showGameOverPopup();
        }
        if(isAllBricksCleared(bricks)) {
            gameFinished = true;
            showGameWinPopup();
        }
    }
    // TODO - Chap07 : 클리어 조건 확인 메서드 (모든 블럭 제거)
    private boolean isAllBricksCleared(List<Brick> bricks) {
        return bricks.isEmpty();
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
    // 게임 승리 팝업 표시하고 종료하는 메서드
    private void showGameWinPopup() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Good Job! All Bricks cleared.", ButtonType.OK);
            alert.setTitle("You Win!");
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