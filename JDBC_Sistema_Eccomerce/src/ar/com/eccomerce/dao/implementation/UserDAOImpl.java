package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.IUserDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;

public class UserDAOImpl implements IUserDAO{
	private Connection conn;
	
	public UserDAOImpl(Connection conn) {
		this.conn  = conn;
	}
	
	
	@Override
	public Boolean insert(User object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(User object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(User newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(User object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
