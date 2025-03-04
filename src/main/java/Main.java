import javafx.application.Application;

import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Canvas 생성 (800 x 600 크기)
        Canvas canvas = new Canvas(800, 600);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
