package ar.com.sistema.secundario.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import ar.com.sistema.secundario.dao.DAOManager;

public class DAOManagerImpl implements DAOManager {
	private Connection conn;
	private final String URL_CONNECTION = "jdbc:mysql://127.0.0.1/sistema_secundario";
	
	private AlumnoDAOImpl daoAlumno;
	private ProfesorDAOImpl daoProfesor;
	private MateriaDAOImpl daoMateria;
	private NotaDAOImpl daoNota;
	private AlumnoMateriaDAOImpl daoAlumnoMateria;
	

	public DAOManagerImpl() throws SQLException {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "");
		this.conn = DriverManager.getConnection(URL_CONNECTION,prop);
	}
	
	
	@Override
	public AlumnoDAOImpl getAlumnoDAO() {
		if(daoAlumno==null)
			this.daoAlumno  = new AlumnoDAOImpl(conn);
		
		return daoAlumno;
	}

	@Override
	public AlumnoMateriaDAOImpl getAlumnoMateriaDAOImpl() {
		if(daoAlumnoMateria==null)
			this.daoAlumnoMateria  = new AlumnoMateriaDAOImpl(conn);
		
		return daoAlumnoMateria;		
	}

	@Override
	public NotaDAOImpl getNotaDAOImpl() {
		if(daoNota==null)
			this.daoNota  = new NotaDAOImpl(conn);
		
		return daoNota;	
	}

	@Override
	public MateriaDAOImpl getMateriaDAOImpl() {
		if(daoMateria==null)
			this.daoMateria  = new MateriaDAOImpl(conn);
		
		return daoMateria;
		
	}

	@Override
	public ProfesorDAOImpl getProfesorDAOImpl() {
		if(daoProfesor==null)
			this.daoProfesor  = new ProfesorDAOImpl(conn);
		
		return daoProfesor;
	}

	public Connection getConnection() {
		return this.conn;
	}
	
	@Override
	public Boolean closeConnection() throws SQLException {
		if(this.conn.isClosed()) {
			return false;
		}
		this.conn.close();
		return true;
	}
}
