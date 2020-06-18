package obstacles;

import java.util.ResourceBundle;

public class HinderedRoomException extends Exception {

    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}
