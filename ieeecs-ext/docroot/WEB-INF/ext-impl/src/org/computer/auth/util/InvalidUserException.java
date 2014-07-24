/**
 * 
 */
package org.computer.auth.util;

/**
 * @author wberks
 *
 */
public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3949710212817585688L;
	
	public InvalidUserException() {
		super();
	}
	
	public InvalidUserException(String message) {
		super(message);
	}

	public InvalidUserException(String message, Exception ex) {
		super(message, ex);
	}

}
