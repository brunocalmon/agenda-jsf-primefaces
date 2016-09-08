package br.com.agenda.exceptions;

/**
 * 
 * @author bruno.calmon
 *
 */
public class AgendaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public AgendaException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * @param msg
	 * @param e
	 */
	public AgendaException(String msg, Exception e) {
		super(msg, e);
	}

	/**
	 * 
	 * @param e
	 */
	public AgendaException(Exception e) {
		super(e);
	}

}
