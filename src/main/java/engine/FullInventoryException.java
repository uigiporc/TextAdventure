package engine;

import java.util.ResourceBundle;

public class FullInventoryException extends Exception{

    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}