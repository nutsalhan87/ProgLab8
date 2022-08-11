package general.route;

import javafx.beans.property.*;

import java.util.Date;

// Ненастоящий Property
public class RouteProperty {
    private final IntegerProperty id;
    private final StringProperty name;
    private final ObjectProperty<Date> creationDate;
    private final DoubleProperty distance;
    private final StringProperty owner;
    // Coordinates
    private final DoubleProperty x;
    private final IntegerProperty y;
    // first.Location
    private final DoubleProperty fromX;
    private final LongProperty fromY;
    private final DoubleProperty fromZ;
    private final StringProperty fromName;
    //second.Location
    private final IntegerProperty toX;
    private final IntegerProperty toY;
    private final FloatProperty toZ;

    public RouteProperty(Route route) {
        id = new SimpleIntegerProperty(route.getId());
        name = new SimpleStringProperty(route.getName());
        creationDate = new SimpleObjectProperty<>(route.getCreationDate());
        distance = new SimpleDoubleProperty(route.getDistance());
        owner = new SimpleStringProperty(route.getOwner());
        x = new SimpleDoubleProperty(route.getCoordinates().getX());
        y = new SimpleIntegerProperty(route.getCoordinates().getY());
        fromX = new SimpleDoubleProperty(route.getFrom().getX());
        fromY = new SimpleLongProperty(route.getFrom().getY());
        fromZ = new SimpleDoubleProperty(route.getFrom().getZ());
        fromName = new SimpleStringProperty(route.getFrom().getName());
        toX = new SimpleIntegerProperty(route.getTo().getX());
        toY = new SimpleIntegerProperty(route.getTo().getY());
        toZ = new SimpleFloatProperty(route.getTo().getZ());
    }

    public IntegerProperty getId() {
        return id;
    }

    public StringProperty getName() {
        return name;
    }

    public ObjectProperty<Date> getCreationDate() {
        return creationDate;
    }

    public DoubleProperty getDistance() {
        return distance;
    }

    public StringProperty getOwner() {
        return owner;
    }

    public DoubleProperty getX() {
        return x;
    }

    public IntegerProperty getY() {
        return y;
    }

    public DoubleProperty getFromX() {
        return fromX;
    }

    public LongProperty getFromY() {
        return fromY;
    }

    public DoubleProperty getFromZ() {
        return fromZ;
    }

    public StringProperty getFromName() {
        return fromName;
    }

    public IntegerProperty getToX() {
        return toX;
    }

    public IntegerProperty getToY() {
        return toY;
    }

    public FloatProperty getToZ() {
        return toZ;
    }
}
