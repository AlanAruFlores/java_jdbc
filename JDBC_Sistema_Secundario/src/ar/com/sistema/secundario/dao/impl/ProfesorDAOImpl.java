package ar.com.sistema.secundario.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.sistema.secundario.dao.ProfesorDAO;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Profesor;

public final class ProfesorDAOImpl implements ProfesorDAO {
	private Connection conn;
	
	public ProfesorDAOImpl (Connection conn) {
		this.conn  = conn;
	}
	
	@Override
	public Boolean insertRow(Profesor object) {
		String query = "INSERT INTO profesor (dni,nombre, apellido) VALUES (?,?,?)";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, object.getDni());
			ps.setString(2, object.getNombre());
			ps.setString(3, object.getApellido());
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Profesor> getAllRows() {
		String query = "SELECT * FROM profesor";
		List<Profesor> listTeachers  = new ArrayList<Profesor>();
		
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
				listTeachers.add(new Profesor(rs.getInt(1), rs.getString(2), rs.getString(3)));
			
			rs.close();
			stmt.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return listTeachers;
	}

	@Override
	public Profesor getRowSpecificById(Integer id) throws EntityNotExistsException {
		String query = "SELECT * FROM profesor WHERE dni = ?";
		Profesor teacher = null;
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				teacher = new Profesor (rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			else {
				rs.close();
				ps.close();
				throw new EntityNotExistsException();
			}
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return teacher;
	}

	@Override
	public Boolean updateRowById(Profesor object) throws EntityNotExistsException {
		String query  = "UPDATE profesor SET nombre = ?, apellido = ? WHERE dni = ?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, object.getNombre());
			ps.setString(2, object.getApellido());
			ps.setInt(3, object.getDni());
			
			var rows = ps.executeUpdate();
			if(rows == 0)
				throw new EntityNotExistsException();
			
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean deleteRowById(Integer id) throws EntityNotExistsException {
		String query  = "DELETE FROM profesor WHERE dni = ?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
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

}
