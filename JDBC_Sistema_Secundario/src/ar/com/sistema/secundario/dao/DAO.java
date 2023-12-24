package ar.com.sistema.secundario.dao;
import java.util.List;

import ar.com.sistema.secundario.exceptions.EntityNotExistsException;

public interface DAO<T>{
	public Boolean insertRow(T object);
	public List<T> getAllRows();
	public T getRowSpecificById(Integer id) throws EntityNotExistsException;
	public Boolean updateRowById(T object) throws EntityNotExistsException;
	public Boolean deleteRowById(Integer id) throws EntityNotExistsException;
}
