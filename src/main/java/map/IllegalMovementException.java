package map;

public class IllegalMovementException extends Exception {
    
    @Override
    public String getMessage() {
	return "Non è possibile muoversi in questa direzione";
    }
}
