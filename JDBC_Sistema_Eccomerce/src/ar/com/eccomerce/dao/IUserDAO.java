package ar.com.eccomerce.dao;

import java.sql.SQLException;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;

public interface IUserDAO extends DAO<User>{
	public User getUserByNameAndPassword(User object) throws SQLException, ObjectSQLNotExists;
	
}
