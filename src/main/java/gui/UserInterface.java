package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class UserInterface extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneControl.launch(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
