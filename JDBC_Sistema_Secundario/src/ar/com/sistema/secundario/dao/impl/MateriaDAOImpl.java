package ar.com.sistema.secundario.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.sistema.secundario.dao.MateriaDAO;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Materia;

public final class MateriaDAOImpl implements MateriaDAO{

	private Connection conn;
	
	public MateriaDAOImpl (Connection conn) {
		this.conn  = conn;
	}
	
	@Override
	public Boolean insertRow(Materia object) {
		String query ="INSERT INTO materia (cod_mat, nombre, dni_pr) VALUES (?,?,?)";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1,object.getCodMateria());
			ps.setString(2, object.getNombre());
			ps.setInt(3, object.getDniProfesor());
			
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return true;
	}

	@Override
	public List<Materia> getAllRows() {
		List<Materia> listSubjects = new ArrayList<>();
		String query = "SELECT * FROM materia";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ResultSet rs  =ps.executeQuery();
			
			while(rs.next()) 
				listSubjects.add(new Materia(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return listSubjects;
	}

	@Override
	public Materia getRowSpecificById(Integer id) throws EntityNotExistsException {
		String query = "SELECT * FROM materia WHERE cod_mat = ?";
		Materia subject = null;
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				subject = new Materia(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}else {
				rs.close();
				ps.close();
				throw new EntityNotExistsException();
			}
			rs.close();
			ps.close();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return subject;
	}

	@Override
	public Boolean updateRowById(Materia object) throws EntityNotExistsException {
		String query = "UPDATE materia SET nombre = ? , dni_pr = ? WHERE cod_mat = ?";
		
		try(PreparedStatement ps =conn.prepareStatement(query)){
			ps.setString(1, object.getNombre());
			ps.setInt(2, object.getDniProfesor());
			ps.setInt(3, object.getCodMateria());
			
			var rows = ps.executeUpdate();
			
			if(rows == 0) {
				ps.close();
				throw new EntityNotExistsException();
			}
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean deleteRowById(Integer id) throws EntityNotExistsException {
		String query  ="DELETE FROM materia WHERE cod_mat = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
			var rows=ps.executeUpdate();
			
			if(rows == 0) {
				ps.close();
				throw new EntityNotExistsException();
			}
			ps.close();			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

}
