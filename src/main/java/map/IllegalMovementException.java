package map;

import java.util.ResourceBundle;

public class IllegalMovementException extends Exception {

    private static final long serialVersionUID = -4997973694139840728L;

    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}
