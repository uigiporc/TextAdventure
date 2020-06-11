package util;

import java.util.ResourceBundle;

public class IllegalActionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975989705665696091L;
	
	@Override
	public String getMessage() {
		return ResourceBundle.getBundle("bundles/ExceptionMessageBundle").getString(this.getClass().getSimpleName());
	}
}
