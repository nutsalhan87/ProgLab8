package client;

import general.Answer;
import general.Request;
import general.User;
import general.route.RouteProperty;
import gui.SceneControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.channels.SocketChannel;

public class BackendInteractions {
    private User user;
    private final SocketChannel socketChannel;
    private final ObservableList<RouteProperty> routePropertyObservableList;

    public BackendInteractions(int port) throws ConnectException {
        socketChannel = Connector.connectedSocket(port);
        routePropertyObservableList = FXCollections.observableArrayList();
    }

    public void authorize(String login, String password) throws AuthorizationException, IOException {
        this.user = Authorizer.logIn(socketChannel, login, password);
    }

    public void register(String login, String password) throws AuthorizationException, IOException {
        this.user = Authorizer.createAccount(socketChannel, login, password);
    }

    public Answer sendRequestAndGetAnswer(Request request) throws IOException {
        SendRequest.sendRequest(request, socketChannel);
        return GetAnswer.getAnswer(socketChannel);
    }

    public User getUser() {
        return user;
    }

    public ObservableList<RouteProperty> getData() {
        return routePropertyObservableList;
    }

    public void setData(ObservableList<RouteProperty> data) {
        routePropertyObservableList.clear();
        routePropertyObservableList.addAll(data);
    }

    public void executeScript(String scriptPath) throws FileNotFoundException {
        SceneControl.openMessage(CommandWorker.executeScript(scriptPath, user, socketChannel));
    }

    public void logOut() {
        try {
            socketChannel.close();
        } catch (IOException ignored) {}
    }
}
