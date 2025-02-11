package ar.com.sistema.secundario.dao.impl;

import java.util.List;

import ar.com.sistema.secundario.dao.AlumnoDAO;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Alumno;
import ar.com.sistema.secundario.utils.Nivel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public final class AlumnoDAOImpl implements AlumnoDAO{
	private Connection conn;
	
	public AlumnoDAOImpl (Connection conn) {
		this.conn  = conn;
	}
	
	@Override
	public Boolean insertRow(Alumno object) {
		String query = "INSERT INTO alumno (dni,nombre,apellido,edad,nivel) VALUES (?,?,?,?,?)";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1,object.getDni());
			ps.setString(2, object.getNombre());
			ps.setString(3,object.getApellido());
			ps.setInt(4, object.getEdad());
			ps.setString(5, object.getNivel().name());
			ps.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Alumno> getAllRows() {
		String query ="SELECT * FROM alumno";
		List<Alumno> listStudents = new ArrayList<Alumno>();
		
		try(PreparedStatement ps=conn.prepareStatement(query)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Nivel level = getLevelOfAStudent(rs.getString(5));
				listStudents.add(new Alumno(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),level));
			}
			rs.close();
			ps.close();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return listStudents;
	}

	private Nivel getLevelOfAStudent(String string) {
		Nivel levelsAllowed[] = Nivel.values();
		
		for(Nivel level : levelsAllowed) 
			if(level.name().equals(string))
				return level;
		throw new RuntimeException("Nivel no existente");
	}

	@Override
	public Alumno getRowSpecificById(Integer id) {
		String query = "SELECT * FROM alumno WHERE dni = ?";
		Alumno student = null;
		try(PreparedStatement ps = this.conn.prepareStatement(query)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Nivel level = getLevelOfAStudent(rs.getString(5));
				student = new Alumno(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),level);

			}else {
				rs.close();
				ps.close();
				return null;
			}
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return student;
	}

	@Override
	public Boolean updateRowById(Alumno object) throws EntityNotExistsException {
		String query = "UPDATE alumno SET nombre=? , apellido=?, edad=?, nivel=? WHERE dni = ?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1,object.getNombre());
			ps.setString(2, object.getApellido());
			ps.setInt(3, object.getEdad());
			ps.setString(4,object.getNivel().name());
			ps.setInt(5,object.getDni());
			var rows = ps.executeUpdate();
			if(rows == 0)
				throw new EntityNotExistsException();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public Boolean deleteRowById(Integer id) throws EntityNotExistsException {
		String query = "DELETE FROM alumno WHERE dni = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setInt(1, id);
			var rows = stmt.executeUpdate();
			if(rows == 0)
				 throw new EntityNotExistsException();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	/*
	public void deletingTable() {
		String query="TRUNCATE TABLE alumno";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	*/

}
