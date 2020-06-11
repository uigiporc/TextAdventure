package obstacles;

import java.util.ResourceBundle;

public class IllegalItemUsageException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 144319835705985733L;

    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}
