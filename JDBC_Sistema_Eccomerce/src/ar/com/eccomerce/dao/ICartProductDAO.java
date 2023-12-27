package ar.com.eccomerce.dao;

import java.sql.SQLException;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.CartProduct;

public interface ICartProductDAO extends DAO<CartProduct>{
	public String showProductsSavedInTheCart(CartProduct objectId) throws SQLException, ObjectSQLNotExists;
}
