package ar.com.sistema.secundario.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.sistema.secundario.dao.AlumnoMateriaDAO;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.AlumnoMateria;

public final class AlumnoMateriaDAOImpl implements AlumnoMateriaDAO{
	private Connection conn;
	
	public AlumnoMateriaDAOImpl (Connection conn) {
		this.conn  = conn;
	}
	
	
	public Boolean insertRow(AlumnoMateria object) {
		String query = "INSERT INTO alumno_materia (dni_al, cod_mat) VALUES (?,?)";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, object.getDniAlumno());
			ps.setInt(2, object.getCodMateria());
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public List<AlumnoMateria> getAllRows() {
		List<AlumnoMateria> listStudentsSubjects = new ArrayList<>();
		String query = "SELECT * FROM alumno_materia";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				listStudentsSubjects.add(new AlumnoMateria(rs.getInt(1),rs.getInt(2)));
			}
			
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return listStudentsSubjects;
	}


	@Override
	public String showAllRowsComplete() {
		String txt = "";
		String query = "select a.dni, a.nombre, a.apellido, m.nombre from alumno_materia al join materia m on al.cod_mat = m.cod_mat join alumno a on al.dni_al = a.dni";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				txt += "ALUMNO: "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3);
				txt += " MATERIA: "+rs.getString(4)+"\n";
			}
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return txt;
	}

	@Override
	//SELECT MULTITABLAS
	public String showSubjectsByStudentId(Integer id) throws EntityNotExistsException {
		String txt = "";
		String query = "select a.dni, a.nombre, a.apellido, m.nombre from alumno_materia al join materia m on al.cod_mat = m.cod_mat join alumno a on al.dni_al = a.dni where al.dni_al = ?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				txt = "ALUMNO: "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3);
				txt += "\nINSCRIPTO EN MATERIAS: \n";
				txt += ""+rs.getString(4);
				
				while(rs.next())
					txt += "\n"+rs.getString(4);
			}
			else{
				rs.close();
				ps.close();
				throw new EntityNotExistsException();
			}
			
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return txt;
	}
	
	
	public Boolean deleteRowByStudentId(Integer id) throws EntityNotExistsException{
		String query = "DELETE FROM alumno_materia WHERE dni_al = ? ";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1,id);
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
	public String showAllStudentsBySubjectId(Integer id) throws EntityNotExistsException, SQLException {
		String query = "select a.nombre, a.apellido, a.dni, m.nombre "
				+ "from alumno_materia am join alumno a on am.dni_al = a.dni "
				+ "join materia m on am.cod_mat = m.cod_mat "
				+ "where m.cod_mat = ?";
		String txt ="";

		PreparedStatement ps =conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs  = ps.executeQuery();
		if(rs.next()) {
			txt="MATERIA: "+rs.getString(4);
			txt+="\nALUMNOS: ";
			txt+="\n"+rs.getInt(3)+" "+rs.getString(1)+" "+rs.getString(2);
			while(rs.next())
				txt+="\n"+rs.getInt(3)+" "+rs.getString(1)+" "+rs.getString(2);

			
		}else {
			rs.close();
			ps.close();
			throw new EntityNotExistsException();
		}
		
		
		return txt;
	}
	
	

}
