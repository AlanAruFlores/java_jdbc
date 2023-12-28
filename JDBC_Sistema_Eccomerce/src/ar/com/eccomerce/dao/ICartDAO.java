package ar.com.eccomerce.dao;

import java.sql.SQLException;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Cart;

public interface ICartDAO {
	public Boolean insert(Cart object) throws SQLException;
	public Cart getCartByUserId (Cart object) throws SQLException, ObjectSQLNotExists;
	public Float getTotalPriceOfCartByCartId(Cart cart)throws SQLException, ObjectSQLNotExists;
}
