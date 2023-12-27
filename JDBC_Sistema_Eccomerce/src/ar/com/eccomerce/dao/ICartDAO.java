package ar.com.eccomerce.dao;

import ar.com.eccomerce.model.Cart;
import ar.com.eccomerce.model.User;

public interface ICartDAO {
	public Cart getCartByUserId (User object);
	public Float getTotalPriceOfCartByCartId(Cart cart);
}
