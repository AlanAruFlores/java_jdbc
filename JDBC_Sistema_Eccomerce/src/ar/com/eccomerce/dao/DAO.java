package ar.com.eccomerce.dao;

import java.sql.SQLException;
import java.util.List;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;

public interface DAO <T>{
	public Boolean insert(T object) throws SQLException;
	public List<T> getAll() throws SQLException;
	public T getById(T object) throws SQLException,ObjectSQLNotExists;
	public Boolean update(T newData) throws SQLException, ObjectSQLNotExists;
	public Boolean delete(T object) throws SQLException ,ObjectSQLNotExists;
}
