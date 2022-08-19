package client;

import general.Answer;
import general.CommandList;
import general.Request;
import general.User;
import general.route.Route;
import general.route.RouteProperty;
import gui.SceneControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public void refreshData() throws IOException {
        Request request = new Request(CommandList.GET_DATA, new LinkedList<>(), getUser());
        Answer answer = sendRequestAndGetAnswer(request);

        ObservableList<RouteProperty> newData = FXCollections.observableArrayList();
        for (Route route : (List<Route>) answer.getAnswer()) {
            newData.add(new RouteProperty(route));
        }
        try {
            Iterator<RouteProperty> iterator = newData.listIterator();
            if (routePropertyObservableList.size() < newData.size() ||
                    routePropertyObservableList.stream().anyMatch(r -> !r.equalsByValues(iterator.next()))) {
                routePropertyObservableList.setAll(newData);
            }
        } catch (NoSuchElementException exception) {
            routePropertyObservableList.setAll(newData);
        }
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
