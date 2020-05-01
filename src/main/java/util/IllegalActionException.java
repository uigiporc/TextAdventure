package util;

public class IllegalActionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975989705665696091L;
	
	@Override
	public String getMessage() {
		return "Azione non consentita";
	}
}
