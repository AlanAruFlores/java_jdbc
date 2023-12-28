package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.com.eccomerce.dao.ICartDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Cart;
public class CartDAOImpl implements ICartDAO{
	private final String insertQuery ="insert into cart (user_id,total_price) values (?,?)";
	private final String getByIdQuery = "select * from cart where user_id = ?";
	private final String getTotalPriceQuery = "select total_price from cart where cod_carr = ? ";
	
	private Connection conn;
		
	public CartDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(Cart object) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setInt(1,object.getUserId());	
		ps.setFloat(2, object.getTotalPrice());
		
		ps.executeUpdate();
		ps.close();
		
		return true;
	}

	
	@Override
	public Cart getCartByUserId(Cart object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(getByIdQuery);
		ps.setInt(1,object.getUserId());
		
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		Cart cartOfUser = new Cart(rs.getInt(1), rs.getInt(2), rs.getFloat(3));
		
		rs.close();
		ps.close();
		return cartOfUser;
	}

	@Override
	public Float getTotalPriceOfCartByCartId(Cart cart) throws SQLException, ObjectSQLNotExists{
		PreparedStatement ps = conn.prepareStatement(getTotalPriceQuery);
		ps.setInt(1, cart.getCodCarr());
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		Float totalPrice = rs.getFloat(1);
		rs.close();
		ps.close();
		return totalPrice;
	}


}
