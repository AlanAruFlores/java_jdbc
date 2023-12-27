package ar.com.eccomerce.exceptions;


public class ObjectSQLNotExists extends Exception {
	private static final long serialVersionUID = -3275179808572874645L;

	public ObjectSQLNotExists() {
		super("SQL Object not exists in the database. Please check again");
	}
}
