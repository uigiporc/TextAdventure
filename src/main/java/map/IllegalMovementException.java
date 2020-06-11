package map;

import java.util.ResourceBundle;

public class IllegalMovementException extends Exception {
    
    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}
