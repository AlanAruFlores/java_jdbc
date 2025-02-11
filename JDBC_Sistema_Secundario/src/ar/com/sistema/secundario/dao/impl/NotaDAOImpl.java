package ar.com.sistema.secundario.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.sistema.secundario.dao.NotaDAO;
import ar.com.sistema.secundario.exceptions.EntityNotExistsException;
import ar.com.sistema.secundario.modelo.Nota;

public final class NotaDAOImpl implements NotaDAO{
	private Connection conn;
	public NotaDAOImpl (Connection conn) {
		this.conn  = conn;
	}
	
	@Override
	public Boolean insertRow(Nota object) {
		String query = "INSERT INTO nota (cod, calificacion, dni_al, dni_pr,cod_mat) VALUES (?,?,?,?,?)";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, object.getCodigo());
			ps.setInt(2, object.getNota());
			ps.setInt(3, object.getDniAlumno());
			ps.setInt(4, object.getDniProfesor());
			ps.setInt(5, object.getCodMateria());
			
			ps.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Nota> getAllRows() {
		List<Nota> listMarks  = new ArrayList<>();
		String query="SELECT * FROM nota";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
				listMarks.add(new Nota(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return listMarks;
	}

	@Override
	public Nota getRowSpecificById(Integer id) throws EntityNotExistsException {
		Nota mark = null;
		String query = "SELECT * FROM nota WHERE cod = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				mark = new Nota(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
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
		
		return mark;
	}

	@Override
	public Boolean updateRowById(Nota object) throws EntityNotExistsException{
		String query = "UPDATE nota SET calificacion = ? , dni_al = ? , dni_pr = ? , cod_mat = ? WHERE cod = ?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, object.getNota());
			ps.setInt(2, object.getDniAlumno());
			ps.setInt(3, object.getDniProfesor());
			ps.setInt(4, object.getCodMateria());
			ps.setInt(5, object.getCodigo());
			
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
	public Boolean deleteRowById(Integer id) throws EntityNotExistsException{
		String query  = "DELETE FROM nota WHERE cod = ?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
			var rows =ps.executeUpdate();
			
			if(rows ==0) {
				ps.close();
				throw new EntityNotExistsException();
			}
			ps.close();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	
	private Double getAverageOfMark(Integer idStudent, Integer idSubject) throws SQLException{
		String query = "select avg(n.calificacion) "
				+ "from nota n join alumno a on n.dni_al = a.dni "
				+ "join profesor p on n.dni_pr = p.dni "
				+ "join materia m on n.cod_mat = m.cod_mat "
				+ "where n.dni_al = ? and n.cod_mat = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, idStudent);
		ps.setInt(2, idSubject);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Double result = rs.getDouble(1);
		return result;
		
	}
	
	
	@Override 
	public String showInformationAboutTheMarksByStudentIdAndSubjectId(Integer idStudent, Integer idSubject) throws EntityNotExistsException {
		String query = "select a.nombre, a.apellido, a.dni, p.nombre, p.apellido, m.nombre, n.calificacion "
				+ "from nota n join alumno a on n.dni_al = a.dni "
				+ "join profesor p on n.dni_pr = p.dni "
				+ "join materia m on n.cod_mat = m.cod_mat "
				+ "where n.dni_al = ? and n.cod_mat = ?";
		
		String txt = "";
		
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, idStudent);
			ps.setInt(2, idSubject);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				txt = "NOTAS DEL ALUMNO: "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3);
				txt+="\nMATERIA: "+rs.getString(6);
				txt+="\nPROFESOR: "+rs.getString(4)+" "+rs.getString(5);
				txt+="\nNOTA: "+rs.getInt(7);
				
				while(rs.next()) {
					txt+="\nNOTA: "+rs.getInt(7);
				}
				
				Double average = getAverageOfMark(idStudent,idSubject);
				txt+="\nPROMEDIO : "+average;
		
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
		
		return txt;
		
	}

	@Override
	public String showHistorialSchoolByStudentId(Integer idStudent) throws SQLException, EntityNotExistsException{
		String query = "select m.cod_mat, m.nombre, avg(n.calificacion), a.nombre, a.apellido "
				+ "from nota n join alumno a on n.dni_al = a.dni "
				+ "join profesor p on n.dni_pr = p.dni "
				+ "join materia m on n.cod_mat = m.cod_mat "
				+ "where n.dni_al = ? "
				+ "group by m.cod_mat, m.nombre, a.nombre, a.apellido";	
		String txt = "";
		PreparedStatement ps =conn.prepareStatement(query);
		ps.setInt(1, idStudent);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			String nameStudent = rs.getString(4)+" "+rs.getString(5);
			txt="HISTORIAL ACADEMICA DE "+nameStudent;	
			txt+="\nCODIGO: "+rs.getString(1)+" | MATERIA: "+rs.getString(2)+" | PROMEDIO: "+rs.getDouble(3);
			
			while(rs.next()) {
				txt+="\nCODIGO: "+rs.getString(1)+" | MATERIA: "+rs.getString(2)+" | PROMEDIO: "+rs.getDouble(3);
			}
		}
		else {
			rs.close();
			ps.close();
			throw new EntityNotExistsException();
		}
	
		rs.close();
		ps.close();
		return txt;
	}
	
}
