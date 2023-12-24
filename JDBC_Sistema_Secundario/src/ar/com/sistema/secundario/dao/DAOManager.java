package ar.com.sistema.secundario.dao;

import java.sql.SQLException;

import ar.com.sistema.secundario.dao.impl.AlumnoDAOImpl;
import ar.com.sistema.secundario.dao.impl.AlumnoMateriaDAOImpl;
import ar.com.sistema.secundario.dao.impl.MateriaDAOImpl;
import ar.com.sistema.secundario.dao.impl.NotaDAOImpl;
import ar.com.sistema.secundario.dao.impl.ProfesorDAOImpl;

public interface DAOManager {
	public AlumnoDAOImpl getAlumnoDAO();
	public AlumnoMateriaDAOImpl getAlumnoMateriaDAOImpl();
	public NotaDAOImpl getNotaDAOImpl();
	public MateriaDAOImpl getMateriaDAOImpl();
	public ProfesorDAOImpl getProfesorDAOImpl();
	
	public Boolean closeConnection() throws SQLException;
}
