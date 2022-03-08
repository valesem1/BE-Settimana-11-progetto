package it.be.progettosettimana12.exception;

public class CatalogoException extends RuntimeException {

	private static final long serialVersionUID = -7068045830079190922L;

	public CatalogoException(String message) {
		super(message);
	}
	
}