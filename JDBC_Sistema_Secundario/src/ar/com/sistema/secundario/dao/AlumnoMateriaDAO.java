package ar.com.sistema.secundario.dao;

import java.sql.SQLException;
import java.util.List;

import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.AlumnoMateria;

public interface AlumnoMateriaDAO{
	public Boolean insertRow(AlumnoMateria object);
	public List<AlumnoMateria> getAllRows();
	public String showAllRowsComplete();
	public String showSubjectsByStudentId(Integer id) throws EntityNotExistsException;
	public Boolean deleteRowByStudentId(Integer id) throws EntityNotExistsException;
	public String showAllStudentsBySubjectId(Integer id) throws EntityNotExistsException, SQLException;
	

}
