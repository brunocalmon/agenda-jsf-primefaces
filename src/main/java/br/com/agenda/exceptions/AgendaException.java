package br.com.agenda.exceptions;

public class AgendaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgendaException(String msg) {
		super(msg);
	}

	public AgendaException(String msg, Exception e) {
		super(msg, e);
	}

	public AgendaException(Exception e) {
		super(e);
	}

}
