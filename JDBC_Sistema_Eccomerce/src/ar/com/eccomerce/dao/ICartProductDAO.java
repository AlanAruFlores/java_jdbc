package ar.com.eccomerce.dao;

import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.CartProduct;

public interface ICartProductDAO{
	public Boolean insert(CartProduct object) throws SQLException;
	public List<CartProduct> getAll() throws SQLException;
	public CartProduct getById(CartProduct object) throws SQLException,ObjectSQLNotExists;
	public String showProductsSavedInTheCart(CartProduct objectId) throws SQLException, ObjectSQLNotExists;
	public Boolean delete(CartProduct object) throws SQLException ,ObjectSQLNotExists;

}
