package ar.com.sistema.secundario.dao;

import java.sql.SQLException;

import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Nota;

public interface NotaDAO extends DAO<Nota>{
	public String showInformationAboutTheMarksByStudentIdAndSubjectId(Integer idStudent, Integer idSubject) throws EntityNotExistsException;
	public String showHistorialSchoolByStudentId(Integer idStudent) throws SQLException, EntityNotExistsException;

}
