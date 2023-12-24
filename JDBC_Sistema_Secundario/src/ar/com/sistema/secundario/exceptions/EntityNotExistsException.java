package ar.com.sistema.secundario.exceptions;

public class EntityNotExistsException extends Exception{
	private static final long serialVersionUID = 1L;

	public EntityNotExistsException() {
		super("THE ENTITY NOT EXISTS ON THE DATABASE");
	}
}

