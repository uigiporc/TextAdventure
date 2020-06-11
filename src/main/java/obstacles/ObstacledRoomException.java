package obstacles;

import java.util.ResourceBundle;

public class ObstacledRoomException extends Exception {

    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}
