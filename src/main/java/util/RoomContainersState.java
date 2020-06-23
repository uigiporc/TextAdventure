package util;

import java.util.ResourceBundle;

public enum RoomContainersState {
    OPEN,
    CLOSED;

    public String getName() {
        return ResourceBundle.getBundle("bundles/ContainerStatusBundle").getString(this.toString());
    }
}
