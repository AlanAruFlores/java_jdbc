package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;

import ar.com.eccomerce.dao.ICartDAO;
import ar.com.eccomerce.model.Cart;
import ar.com.eccomerce.model.User;

public class CartDAOImpl implements ICartDAO{
	private Connection conn;
	
	public CartDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Cart getCartByUserId(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getTotalPriceOfCartByCartId(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

}
