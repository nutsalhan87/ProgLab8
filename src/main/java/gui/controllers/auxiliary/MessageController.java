package gui.controllers.auxiliary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {
    @FXML
    private Text message;
    @FXML
    public Button closeWindowButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setMessage(String msg) {
        message.setText(msg);
    }

    @FXML
    public void darkenButton(MouseEvent mouseEvent) {
        closeWindowButton.setStyle("-fx-background-color: grey");
    }

    @FXML
    public void makeButtonTransparent(MouseEvent mouseEvent) {
        closeWindowButton.setStyle("-fx-background-color: transparent");
    }

    @FXML
    private void makeButtonRed(MouseEvent mouseEvent) {
        closeWindowButton.setStyle("-fx-background-color: red");
    }
}
