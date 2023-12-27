package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eccomerce.dao.IUserStateDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.UserState;

public class UserStateDAOImpl implements IUserStateDAO{
	private final String insertQuery = "insert into user_state (name) values (?)";
	private final String getAllQuery = "select * from user_state";
	private final String getByIdQuery = "select * from user_state where cod_sta = ?";
	private final String updateQuery = "update user_state set name = ? where cod_sta = ?";
	private final String deleteQuery = "delete from user_state where cod_sta = ? ";
	
	private Connection conn;
	
	public UserStateDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(UserState object) throws SQLException {
		try(PreparedStatement ps = conn.prepareStatement(insertQuery)){
			ps.setString(1, object.getName());
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException ex ) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public List<UserState> getAll() throws SQLException {
		List<UserState> listUsersStates = new ArrayList<UserState>();
		try(PreparedStatement ps = conn.prepareStatement(getAllQuery)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				UserState aux  = new UserState(rs.getInt(1), rs.getString(2));
				listUsersStates.add(aux);
			}
				
			rs.close();
			ps.close();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return listUsersStates;
	}

	@Override
	public UserState getById(UserState object) throws SQLException, ObjectSQLNotExists {
		UserState obj = null;
		PreparedStatement ps =  conn.prepareStatement(getByIdQuery);
		ps.setInt(1,object.getCodSta());
		ResultSet rs = ps.executeQuery();

		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		obj = new UserState(rs.getInt(1), rs.getString(2));
		rs.close();
		ps.close();
		
		return obj;
	}

	@Override
	public Boolean update(UserState newData) throws SQLException, ObjectSQLNotExists {
		var rows = 0;
		PreparedStatement ps  = conn.prepareStatement(updateQuery);
		ps.setString(1,newData.getName());
		ps.setInt(2,newData.getCodSta());
		
		rows = ps.executeUpdate();
		
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		ps.close();
		return true;
	}

	@Override
	public Boolean delete(UserState object) throws SQLException, ObjectSQLNotExists {
		var rows = 0;
		PreparedStatement ps = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getCodSta());
		
		rows = ps.executeUpdate();
		if(rows  ==0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		ps.close();
		return true;
	}

}
