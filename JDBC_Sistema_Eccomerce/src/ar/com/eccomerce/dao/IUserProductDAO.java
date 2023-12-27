package ar.com.eccomerce.dao;

import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;
import ar.com.eccomerce.model.UserProduct;

public interface IUserProductDAO {
	public Boolean insert(UserProduct object) throws SQLException;
	public List<UserProduct> getAll() throws SQLException;
	public String showAllProductsByUserId(User userId) throws SQLException, ObjectSQLNotExists;
}
