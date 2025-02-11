package ar.com.sistema.secundario.interfaz;

import java.util.Properties;
import java.sql.*;
public class ArchivoEjemplo {

	
	private static Properties getPropertiesOfDatabaseConnection() {
		Properties propierties = new Properties();
		propierties.setProperty("user", "root");
		propierties.setProperty("password", "");
		
		return propierties;
	}
	
	private static Connection getConnectionDatabase() {
		try{
			System.out.println("Succesfully");
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sistema_secundario", getPropertiesOfDatabaseConnection());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private static void closeConnection(Connection conn) {
		try{
			if(!conn.isClosed()) {
				conn.close();
				System.out.println("Connection is closed now");
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * En este metodo nos sirve para ver cada uno de los registros de los estudiantes 
	 * pero el uso de Statement no es recomendable debido a su nivel de seguridad
	 * @param conn
	 */
	private static void seeSpecificStudentStatement(Connection conn, int id) {
		String query = "SELECT * FROM alumno WHERE dni = "+id;
		try(Statement stmt = conn.createStatement()){ 
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int dni = rs.getInt("dni");
				String nombre = rs.getString("nombre");
				String apellido  = rs.getString("apellido");
				int edad = rs.getInt("edad");
				String nivel = rs.getString("nivel");
				System.out.println(dni+" - "+nombre+" - "+apellido+" - "+edad+" - "+nivel);
			}
			rs.close();
			stmt.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	private static void seeSpecificStudentPreparedStatement(Connection conn , int  id) {
		String query = "SELECT * FROM alumno WHERE dni = ?";

		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int dni = rs.getInt("dni");
				String nombre = rs.getString("nombre");
				String apellido  = rs.getString("apellido");
				int edad = rs.getInt("edad");
				String nivel = rs.getString("nivel");
				System.out.println(dni+" - "+nombre+" - "+apellido+" - "+edad+" - "+nivel);
			}
			rs.close();
			ps.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	private static void insertRowInDB(Connection conn, int dni, String nombre, String apellido, int edad, String nivel) {
		String query = "INSERT INTO alumno (dni,nombre,apellido,edad,nivel) VALUES(?,?,?,?,?)";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setInt(1, dni);			
			stmt.setString(2, nombre);
			stmt.setString(3, apellido);
			stmt.setInt(4, edad);
			stmt.setString(5, nivel);

			int cont = stmt.executeUpdate();
			if(cont != 0)
				System.out.println("A row is inserted in alumno's table");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	private static void seeDrivers() {
		//RECORREMOS LOS DRIVERS DE NUESTRO PROGRAMNA
		DriverManager.drivers().forEach((x)->{
			System.out.println(x.toString());
		});
	}
	
	public static void main(String[] args) {
		Connection conn = getConnectionDatabase();
		seeSpecificStudentPreparedStatement(conn,4221223);
		insertRowInDB(conn,4433333,"Mario", "Bros", 40, "CUARTO");
		closeConnection(conn);
		
		System.out.println("DRIVERS THAT IS USING THE PROJECT");
		seeDrivers();
	}
	
	
}
