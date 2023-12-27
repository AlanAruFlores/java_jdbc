package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eccomerce.dao.IUserDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;

public class UserDAOImpl implements IUserDAO{
	private final String insertQuery = "insert into user(name,password,is_admin,mail,money,cod_sta) values(?,?,?,?,?,?)";
	private final String getAllQuery = "select * from user";
	private final String getByIdQuery = "select * from user where id = ?";
	private final String updateQuery = "update user set name = ? , password = ? , mail=?, is_admin = ?, money = ?, cod_sta = ? where id = ?";
	private final String deleteQuery = " delete from user where id = ?";
	
	private Connection conn;
	
	public UserDAOImpl(Connection conn) {
		this.conn  = conn;
	}
	
	
	@Override
	public Boolean insert(User object) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setString(1,object.getName());
		ps.setString(2, object.getPassword());
		ps.setInt(3,object.getIsAdmin() ? 1:0);
		ps.setString(4, object.getMail());
		ps.setFloat(5, object.getMoney());
		ps.setInt(6, object.getCodSta());
		
		ps.executeUpdate();
		return true;
	}

	@Override
	public List<User> getAll() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		ResultSet rs = ps.executeQuery();
		List<User> listOfUsers = new ArrayList<User>();
		
		while(rs.next()) {
			User aux = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(6),rs.getInt(4) == 1 ? true : false,rs.getString(5),rs.getInt(7));
			listOfUsers.add(aux);
		}
		
		rs.close();
		ps.close();
		return listOfUsers;
	}

	@Override
	public User getById(User object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(getByIdQuery);
		ps.setInt(1, object.getId());
		
		ResultSet rs = ps.executeQuery();
	
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(6),rs.getInt(4) == 1 ? true : false,rs.getString(5),rs.getInt(7));
		rs.close();
		ps.close();
		return user;
	}

	@Override
	public Boolean update(User newData) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(updateQuery);
		ps.setString(1, newData.getName());
		ps.setString(2, newData.getPassword());
		ps.setString(3,newData.getMail());
		ps.setInt(4, newData.getIsAdmin() ? 1:0);
		ps.setFloat(5, newData.getMoney());
		ps.setInt(6, newData.getCodSta());
		ps.setInt(7, newData.getId());
		
		var rows = ps.executeUpdate();
		
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		ps.close();
		
		return true;
	}

	@Override
	public Boolean delete(User object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getId());
		
		var rows = ps.executeUpdate();
		
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		ps.close();
		
		return true;
	}

}
