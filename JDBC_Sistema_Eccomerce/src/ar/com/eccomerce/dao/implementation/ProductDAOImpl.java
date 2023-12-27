package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.IProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Product;

public class ProductDAOImpl implements IProductDAO{
	private Connection conn;
	
	public ProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public Boolean insert(Product object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getById(Product object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Product newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Product object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
