package obstacles;

public class IllegalItemUsageException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 144319835705985733L;

    @Override
    public String getMessage() {
    	return "Non puoi usare lo strumento in questo modo.";
    }
}
