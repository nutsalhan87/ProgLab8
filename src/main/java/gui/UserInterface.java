package gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInterface extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneControl.launch(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
