package gui.controllers.main;

import general.Answer;
import general.CommandList;
import general.Request;
import general.route.Route;
import general.route.RouteProperty;
import gui.*;
import gui.controllers.auxiliary.side.AbstractSideMenu;
import gui.controllers.auxiliary.side.EditMenuController;
import client.locales.LocaleManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WorkspaceController implements Initializable, TextDrawable {
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    public TableView<RouteProperty> table;
    @FXML
    private FilteredTableColumn<RouteProperty, Integer> idColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, String> nameColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Double> xColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Integer> yColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Date> dateColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Double> fromXColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Long> fromYColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Double> fromZColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, String> fromNameColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Integer> toXColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Integer> toYColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Float> toZColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, Double> distanceColumn;
    @FXML
    private FilteredTableColumn<RouteProperty, String> ownerColumn;
    @FXML
    private Button infoButton;
    @FXML
    private Button trashButton;
    @FXML
    private Button uploadButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button userButton;
    @FXML
    private Button scriptButton;
    @FXML
    private Button visualizationButton;
    @FXML
    private Button languageButton;
    @FXML
    private Label tableHeading;

    private LanguagePopup languagePopup;
    private ScriptPopup scriptPopup;
    private UserPopup userPopup;

    private FilterPopup<RouteProperty, Integer> idFilter;
    private FilterPopup<RouteProperty, String> nameFilter;
    private FilterPopup<RouteProperty, Double> xFilter;
    private FilterPopup<RouteProperty, Integer> yFilter;
    private FilterPopup<RouteProperty, Date> dateFilter;
    private FilterPopup<RouteProperty, Double> fromXFilter;
    private FilterPopup<RouteProperty, Long> fromYFilter;
    private FilterPopup<RouteProperty, Double> fromZFilter;
    private FilterPopup<RouteProperty, String> fromNameFilter;
    private FilterPopup<RouteProperty, Integer> toXFilter;
    private FilterPopup<RouteProperty, Integer> toYFilter;
    private FilterPopup<RouteProperty, Float> toZFilter;
    private FilterPopup<RouteProperty, Double> distanceFilter;
    private FilterPopup<RouteProperty, String> ownerFilter;
    private List<FilterPopup<RouteProperty, ?>> filters;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocaleManager.addListener(this);
        drawText();

        initializeTable();

        // Открытие EditMenu по дабл-клику
        table.setRowFactory(tv -> {
            TableRow<RouteProperty> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
                    RouteProperty rowData = row.getItem();
                    URL url = SceneControl.class.getClassLoader().getResource("layouts/auxiliary/EditMenu.fxml");
                    FXMLLoader loader = new FXMLLoader(url);
                    try {
                        Answer answer = SceneControl.getBackendInteractor().sendRequestAndGetAnswer(
                                new Request(CommandList.CHECK_ID, Collections.singletonList(rowData.getId().getValue()),
                                SceneControl.getBackendInteractor().getUser()));
                        if (answer.getAnswer().equals("TRUE")) {
                            openSideMenu(loader);
                            loader.<EditMenuController>getController().setFields(rowData);
                            loader.<EditMenuController>getController().setIdRoute(rowData.getId().getValue());
                        }
                    } catch (IOException ioexc) {
                        SceneControl.openMessage(ioexc.getMessage());
                    }
                }
            });
            return row;
        });

        idFilter = new FilterPopup<>(idColumn, S -> S.getId().get());
        nameFilter = new FilterPopup<>(nameColumn, S -> S.getName().get());
        xFilter = new FilterPopup<>(xColumn, S -> S.getX().get());
        yFilter = new FilterPopup<>(yColumn, S -> S.getY().get());
        dateFilter = new FilterPopup<>(dateColumn, S -> S.getCreationDate().get());
        fromXFilter = new FilterPopup<>(fromXColumn, S -> S.getFromX().get());
        fromYFilter = new FilterPopup<>(fromYColumn, S -> S.getFromY().get());
        fromZFilter = new FilterPopup<>(fromZColumn, S -> S.getFromZ().get());
        fromNameFilter = new FilterPopup<>(fromNameColumn, S -> S.getFromName().get());
        toXFilter = new FilterPopup<>(toXColumn, S -> S.getToX().get());
        toYFilter = new FilterPopup<>(toYColumn, S -> S.getToY().get());
        toZFilter = new FilterPopup<>(toZColumn, S -> S.getToZ().get());
        distanceFilter = new FilterPopup<>(distanceColumn, S -> S.getDistance().get());
        ownerFilter = new FilterPopup<>(ownerColumn, S -> S.getOwner().get());
        filters = new LinkedList<>(Arrays.asList(idFilter, nameFilter, xFilter, yFilter, dateFilter, fromXFilter,
                fromYFilter, fromZFilter, fromNameFilter, toXFilter, toYFilter, toZFilter, distanceFilter, ownerFilter));

        languagePopup = new LanguagePopup(languageButton);
        scriptPopup = new ScriptPopup(scriptButton);
        userPopup = new UserPopup(userButton);
    }

    @Override
    public void drawText() {
        userButton.setText(SceneControl.getBackendInteractor().getUser().getUser());
        scriptButton.setText(LocaleManager.getString("script"));
        visualizationButton.setText(LocaleManager.getString("map"));
        languageButton.setText(LocaleManager.getString("language"));
        tableHeading.setText(LocaleManager.getString("dagestanBase"));
        dateColumn.setText(LocaleManager.getString("creationDate"));
        distanceColumn.setText(LocaleManager.getString("distance"));
        fromNameColumn.setText(LocaleManager.getString("fromName"));
        nameColumn.setText(LocaleManager.getString("name"));
        ownerColumn.setText(LocaleManager.getString("owner"));
    }

    private void initializeTable() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        xColumn.setCellValueFactory(cellData -> cellData.getValue().getX().asObject());
        yColumn.setCellValueFactory(cellData -> cellData.getValue().getY().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().getCreationDate());
        fromXColumn.setCellValueFactory(cellData -> cellData.getValue().getFromX().asObject());
        fromYColumn.setCellValueFactory(cellData -> cellData.getValue().getFromY().asObject());
        fromZColumn.setCellValueFactory(cellData -> cellData.getValue().getFromZ().asObject());
        fromNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFromName());
        toXColumn.setCellValueFactory(cellData -> cellData.getValue().getToX().asObject());
        toYColumn.setCellValueFactory(cellData -> cellData.getValue().getToY().asObject());
        toZColumn.setCellValueFactory(cellData -> cellData.getValue().getToZ().asObject());
        distanceColumn.setCellValueFactory(cellData -> cellData.getValue().getDistance().asObject());
        ownerColumn.setCellValueFactory(cellData -> cellData.getValue().getOwner());

        idColumn.setOnFilterAction(event -> idFilter.showPopup());
        nameColumn.setOnFilterAction(event -> nameFilter.showPopup());
        xColumn.setOnFilterAction(event -> xFilter.showPopup());
        yColumn.setOnFilterAction(event -> yFilter.showPopup());
        dateColumn.setOnFilterAction(event -> dateFilter.showPopup());
        fromXColumn.setOnFilterAction(event -> fromXFilter.showPopup());
        fromYColumn.setOnFilterAction(event -> fromYFilter.showPopup());
        fromZColumn.setOnFilterAction(event -> fromZFilter.showPopup());
        fromNameColumn.setOnFilterAction(event -> fromNameFilter.showPopup());
        toXColumn.setOnFilterAction(event -> toXFilter.showPopup());
        toYColumn.setOnFilterAction(event -> toYFilter.showPopup());
        toZColumn.setOnFilterAction(event -> toZFilter.showPopup());
        distanceColumn.setOnFilterAction(event -> distanceFilter.showPopup());
        ownerColumn.setOnFilterAction(event -> ownerFilter.showPopup());
    }

    public ObservableList<RouteProperty> checkFilter(ObservableList<RouteProperty> data) {
        ObservableList<RouteProperty> filteredData = FXCollections.observableArrayList(data);
        for (FilterPopup<?, ?> filter : filters) {
            filteredData = filter.checkFilter(filteredData);
        }
        return filteredData;
    }

    private StackPane getTranslucentBackground() {
        StackPane translucentBackground = new StackPane();
        AnchorPane.setLeftAnchor(translucentBackground, 0D);
        AnchorPane.setRightAnchor(translucentBackground, 0D);
        AnchorPane.setTopAnchor(translucentBackground, 0D);
        AnchorPane.setBottomAnchor(translucentBackground, 0D);
        translucentBackground.setStyle("-fx-background-color: black;");
        translucentBackground.setOpacity(0.25D);

        return translucentBackground;
    }

    private void openSideMenu(FXMLLoader loader) throws IOException {
        AnchorPane sideMenu = loader.load();
        StackPane translucentBackground = getTranslucentBackground();

        mainAnchor.getChildren().add(translucentBackground);
        mainAnchor.getChildren().add(sideMenu);

        AnchorPane.setTopAnchor(sideMenu, 0D);
        AnchorPane.setBottomAnchor(sideMenu, 0D);
        AnchorPane.setLeftAnchor(sideMenu, 0D);

        loader.<AbstractSideMenu>getController().closeMenuButton.setOnAction(a -> {
            mainAnchor.getChildren().remove(translucentBackground);
            mainAnchor.getChildren().remove(sideMenu);
        });
        translucentBackground.setOnMouseClicked(value -> {
            mainAnchor.getChildren().remove(translucentBackground);
            mainAnchor.getChildren().remove(sideMenu);
        });

        sideMenu.requestFocus();
    }

    @FXML
    private void openLanguageSelector(ActionEvent actionEvent) {
        languagePopup.showPopup();
    }

    @FXML
    private void openScriptExecutor(ActionEvent actionEvent) {
        scriptPopup.showPopup();
    }

    @FXML
    private void openUserMenu(ActionEvent actionEvent) {
        userPopup.showPopup();
    }

    @FXML
    public void refreshData(ActionEvent actionEvent) throws IOException {
        Request request = new Request(CommandList.GET_DATA, new LinkedList<>(),
                SceneControl.getBackendInteractor().getUser());
        Answer answer = SceneControl.getBackendInteractor().sendRequestAndGetAnswer(request);

        ObservableList<RouteProperty> routePropertyObservableList = FXCollections.observableArrayList();
        for (Route route : (List<Route>) answer.getAnswer()) {
            routePropertyObservableList.add(new RouteProperty(route));
        }
        SceneControl.getBackendInteractor().setData(routePropertyObservableList);

        table.setItems(checkFilter(routePropertyObservableList));
    }

    @FXML
    private void getInfo(ActionEvent actionEvent) throws IOException {
        Request request = new Request(CommandList.INFO, new LinkedList<>(),
                SceneControl.getBackendInteractor().getUser());
        Answer answer = SceneControl.getBackendInteractor().sendRequestAndGetAnswer(request);
        SceneControl.openMessage((String) answer.getAnswer());
    }

    @FXML
    private void openAddMenu(ActionEvent actionEvent) throws IOException {
        URL url = SceneControl.class.getClassLoader().getResource("layouts/auxiliary/AddMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        openSideMenu(loader);
    }

    @FXML
    private void openRemoveMenu(ActionEvent actionEvent) throws IOException {
        URL url = SceneControl.class.getClassLoader().getResource("layouts/auxiliary/RemoveMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        openSideMenu(loader);
    }

    @FXML
    private void resetFilters(ActionEvent actionEvent) {
        for (FilterPopup<RouteProperty, ?> filterPopup : filters) {
            filterPopup.resetFilter();
        }
    }
}
