package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.IUserProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;
import ar.com.eccomerce.model.UserProduct;

public class UserProductDAOImpl implements IUserProductDAO{
	private Connection conn;
	
	public UserProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(UserProduct object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserProduct> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showAllProductsByUserId(User userId) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
