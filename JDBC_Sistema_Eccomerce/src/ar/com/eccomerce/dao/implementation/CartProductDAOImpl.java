package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.ICartProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.CartProduct;

public class CartProductDAOImpl implements ICartProductDAO{
	private Connection conn;
	
	public CartProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public Boolean insert(CartProduct object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartProduct> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartProduct getById(CartProduct object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(CartProduct newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(CartProduct object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showProductsSavedInTheCart(CartProduct objectId) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
