package gui.controllers.auxiliary.side;

import client.workwithroute.RouteFields;
import client.workwithroute.ValuesChecking;
import general.CommandList;
import general.Request;
import general.route.Route;
import gui.SceneControl;
import gui.TextDrawable;
import client.locales.LocaleManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class RemoveMenuController extends AbstractSideMenu implements Initializable, TextDrawable {
    @FXML
    private Button removeLowerButton;
    @FXML
    private Button removeGreaterButton;
    @FXML
    private Button distanceRemoveButton;
    @FXML
    private Label distanceLabel;
    @FXML
    private Label fromNameLabel;
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocaleManager.addListener(this);
        drawText();
    }

    @Override
    public void drawText() {
        removeLowerButton.setText(LocaleManager.getString("lowerRemove"));
        removeGreaterButton.setText(LocaleManager.getString("greaterRemove"));
        distanceRemoveButton.setText(LocaleManager.getString("distanceRemove"));
        distanceLabel.setText(LocaleManager.getString("distance"));
        fromNameLabel.setText(LocaleManager.getString("fromName"));
        nameLabel.setText(LocaleManager.getString("name"));
    }

    @FXML
    private void removeDistance(ActionEvent actionEvent) throws IOException {
        try {
            String distance = ValuesChecking.checkCondition(distanceField.getText(), RouteFields.R_DISTANCE);
            SceneControl.getBackendInteractor().sendRequestAndGetAnswer(new Request(CommandList.REMOVE_ANY_BY_DISTANCE,
                    Collections.singletonList(distance), SceneControl.getBackendInteractor().getUser()));
            closeMenuButton.fire();
            SceneControl.getWorkspaceController().refreshData(new ActionEvent());
        } catch (IllegalArgumentException iaexc) {
            SceneControl.openMessage(LocaleManager.getString(iaexc.getMessage()));
        }
    }

    @FXML
    private void removeLower(ActionEvent actionEvent) throws IOException {
        try {
            Route route = getRouteFromFields();
            SceneControl.getBackendInteractor().sendRequestAndGetAnswer(new Request(CommandList.REMOVE_LOWER,
                    Collections.singletonList(route), SceneControl.getBackendInteractor().getUser()));
            closeMenuButton.fire();
            SceneControl.getWorkspaceController().refreshData(new ActionEvent());
        } catch (IllegalArgumentException iaexc) {
            SceneControl.openMessage(LocaleManager.getString(iaexc.getMessage()));
        }
    }

    @FXML
    private void removeGreater(ActionEvent actionEvent) throws IOException {
        try {
            Route route = getRouteFromFields();
            SceneControl.getBackendInteractor().sendRequestAndGetAnswer(new Request(CommandList.REMOVE_GREATER,
                    Collections.singletonList(route), SceneControl.getBackendInteractor().getUser()));
            closeMenuButton.fire();
            SceneControl.getWorkspaceController().refreshData(new ActionEvent());
        } catch (IllegalArgumentException iaexc) {
            SceneControl.openMessage(LocaleManager.getString(iaexc.getMessage()));
        }
    }
}
