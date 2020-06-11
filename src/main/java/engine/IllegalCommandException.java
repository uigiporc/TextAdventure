package engine;

import java.util.ResourceBundle;

public class IllegalCommandException extends Exception{

    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}
