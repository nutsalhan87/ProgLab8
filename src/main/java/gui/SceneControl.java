package gui;

import client.BackendInteractions;
import gui.controllers.auxiliary.MessageController;
import gui.controllers.main.WorkspaceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;

public class SceneControl {
    private static Stage stage;
    private static BackendInteractions backendInteractor;
    private static FXMLLoader workspaceLoader;

    public static void launch(Stage primaryStage) {
        stage = primaryStage;
        openWelcomeMenu();
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        stage.show();
    }

    public static BackendInteractions getBackendInteractor() {
        return backendInteractor;
    }

    public static void connectToPort(int port) throws ConnectException {
        backendInteractor = new BackendInteractions(port);
    }

    public static void openWelcomeMenu() {
        try {
            URL url = SceneControl.class.getClassLoader().getResource("layouts/main/WelcomeMenu.fxml");
            stage.setScene(FXMLLoader.load(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openAuthorizeMenu() {
        try {
            URL url = SceneControl.class.getClassLoader().getResource("layouts/main/Authorizing.fxml");
            stage.setScene(FXMLLoader.load(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openRegisterMenu() {
        try {
            URL url = SceneControl.class.getClassLoader().getResource("layouts/main/Registering.fxml");
            stage.setScene(FXMLLoader.load(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openWorkspace() {
        try {
            URL url = SceneControl.class.getClassLoader().getResource("layouts/main/Workspace.fxml");
            workspaceLoader = new FXMLLoader(url);
            stage.setScene(workspaceLoader.load());
            getWorkspaceController().refreshData(new ActionEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openMessage(String message) {
        try {
            URL url = SceneControl.class.getClassLoader().getResource("layouts/auxiliary/Message.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(url);

            Stage messageStage = new Stage();
            Scene messageScene = new Scene(fxmlLoader.load());

            messageStage.initOwner(stage);
            messageStage.setScene(messageScene);
            messageStage.setResizable(false);
            messageStage.initStyle(StageStyle.UNDECORATED);

            fxmlLoader.<MessageController>getController().setMessage(message);
            fxmlLoader.<MessageController>getController().closeWindowButton.
                    setOnAction((ActionEvent actionEvent) -> messageStage.close());

            messageStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WorkspaceController getWorkspaceController() {
        return workspaceLoader.getController();
    }

    public static void logOut() {
        backendInteractor.logOut();
        openWelcomeMenu();
    }
}
