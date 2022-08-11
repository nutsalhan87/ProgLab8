package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FilteredTableColumn<S, T> extends TableColumn<S, T> {
    private final Button filterButton;

    public FilteredTableColumn() {
        super();
        filterButton = new Button();
        ImageView filterIcon = new ImageView(new Image("icons/filterIconBlack.png"));
        filterIcon.setPreserveRatio(true);
        filterIcon.setFitWidth(10);
        filterButton.setContentDisplay(ContentDisplay.LEFT);
        filterButton.setGraphic(filterIcon);
        this.setGraphic(filterButton);
    }

    public void setOnFilterAction(EventHandler<ActionEvent> event) {
        filterButton.setOnAction(event);
    }
}
