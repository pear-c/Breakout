import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Canvas 생성 (800 x 600 크기)
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // 기본 배경 설정
        gc.setFill(javafx.scene.paint.Color.BLACK); // 배경색 검정
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); // 전체 채우기

        // 점수 표시 라벨
        Label scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");



//        // VBox에 점수 표시 추가
//        VBox uiOverlay = new VBox();
//        uiOverlay.getChildren().add(scoreLabel);
//        uiOverlay.setStyle("-fx-alignment: top-center; -fx-padding: 10;");
//
//        // StackPane에 Canvas와 UI 레이어 추가
//        StackPane root = new StackPane();
//        root.getChildren().addAll(canvas, uiOverlay);

        // 하단 버튼
        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-font-size: 16px;");

        BorderPane root = new BorderPane();
        root.setTop(scoreLabel);
        root.setCenter(canvas);
        root.setBottom(restartButton);

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
