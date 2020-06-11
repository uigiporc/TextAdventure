package items;

import java.util.ResourceBundle;

public class ItemNotFoundException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3126436708386427606L;

    @Override
    public String getMessage() {
        return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
    }
}
