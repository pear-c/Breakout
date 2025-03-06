module com.nhnacademy {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive javafx.graphics;

    opens BrickBreaker to javafx.fxml;

    exports BrickBreaker;
}

