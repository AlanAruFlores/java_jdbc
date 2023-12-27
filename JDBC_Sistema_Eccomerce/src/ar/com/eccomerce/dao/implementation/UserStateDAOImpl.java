package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.IUserStateDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.UserState;

public class UserStateDAOImpl implements IUserStateDAO{
	private Connection conn;
	
	public UserStateDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(UserState object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserState> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserState getById(UserState object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(UserState newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(UserState object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
