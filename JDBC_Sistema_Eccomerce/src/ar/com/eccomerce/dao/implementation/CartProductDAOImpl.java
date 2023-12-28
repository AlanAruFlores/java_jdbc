package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eccomerce.dao.ICartProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.CartProduct;

public class CartProductDAOImpl implements ICartProductDAO{

	private final String insertQuery = "insert into cart_product(cod_carr,cod_prod) values (?,?)";
	private final String getAllQuery  = "select * from cart_product";
	private final String getByIdQuery = "select * from cart_product where cod_carr = ? and cod_prod = ?";
	private final String deleteQuery ="delete from cart_product where cod_carr = ? and cod_prod = ?";
	private final String showProductsByCartId = "select cp.cod_carr, p.cod_prod, p.name from cart_product cp join product p on cp.cod_prod  = p.cod_prod "
			+ "where cp.cod_carr = ?";
	private Connection conn;
	
	public CartProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public Boolean insert(CartProduct object) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setInt(1, object.getCodCarr());
		ps.setInt(2, object.getCodProd());
		
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public List<CartProduct> getAll() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		ResultSet rs = ps.executeQuery();
		List<CartProduct> list = new ArrayList<>();
		
		while(rs.next()) {
			CartProduct cp = new CartProduct(rs.getInt(1),rs.getInt(2));
			list.add(cp);
		}
		
		rs.close();
		ps.close();
		
		return list;
	}

	@Override
	public CartProduct getById(CartProduct object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(getByIdQuery);
		ps.setInt(1, object.getCodCarr());
		ps.setInt(2, object.getCodProd());
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		CartProduct cp = new CartProduct(rs.getInt(1), rs.getInt(2));
		return cp;
	}

	@Override
	public Boolean delete(CartProduct object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps  = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getCodCarr());
		ps.setInt(2, object.getCodProd());
		
		var rows = ps.executeUpdate();
		
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		ps.close();
		return true;	}

	@Override
	public String showProductsSavedInTheCart(CartProduct objectId) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(showProductsByCartId);
		ps.setInt(1, objectId.getCodCarr());
		ResultSet rs = ps.executeQuery();
		
		String txt = "";
		if(rs.next()) {
			txt += "PRODUCTS OF THE CART WHICH ID IS: "+rs.getInt(1)+"\n";
			txt += "PRODUCT ID: "+rs.getInt(2)+" NAME: "+rs.getString(3)+"\n";
			
			while(rs.next()) 
				txt += "PRODUCT ID: "+rs.getInt(2)+" NAME: "+rs.getString(3)+"\n";
		}
		else {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		rs.close();
		ps.close();
		return txt;
	}

}
