package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;

public class UserPopup extends PopupControl {
    Button userButton;

    public UserPopup(Button userButton) {
        super();
        this.userButton = userButton;
        this.setAutoHide(true);
        this.setAutoFix(true);
        this.setHideOnEscape(true);
        this.setSkin(createDefaultSkin());
    }

    public void showPopup() {
        if (userButton.getScene() != null && userButton.getScene().getWindow() != null) {
            if (!this.isShowing()) {
                Window parent = userButton.getScene().getWindow();
                this.show(parent, parent.getX() + userButton.localToScene(0.0D, 0.0D).getX() +
                                userButton.getScene().getX() + ((userButton.getWidth() - ((VBox)getSkin().getNode()).getWidth()) / 2),
                        parent.getY() + userButton.localToScene(0.0D, 0.0D).getY()
                                + userButton.getScene().getY() + userButton.getLayoutBounds().getHeight());
            }
        } else {
            throw new IllegalStateException("Can not show popup. The node must be attached to a scene/window.");
        }
    }

    protected Skin<?> createDefaultSkin() {
        try {
            return new UserPopupSkin(this);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class UserPopupSkin implements Skin<UserPopup> {
        private final VBox userMenu;
        private final UserPopup userPopup;

        public UserPopupSkin(UserPopup userPopup) throws IOException {
            this.userPopup = userPopup;
            URL url = FilterPopup.class.getClassLoader().getResource("layouts/auxiliary/UserMenu.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            userMenu = loader.load();
        }

        @Override
        public UserPopup getSkinnable() {
            return userPopup;
        }

        @Override
        public Node getNode() {
            return userMenu;
        }

        @Override
        public void dispose() {}
    }
}
