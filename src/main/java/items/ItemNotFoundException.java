package items;

public class ItemNotFoundException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3126436708386427606L;

    @Override
    public String getMessage() {
	return "Strumento non trovato nell'invetario.";
    }

}
