package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ar.com.eccomerce.dao.IUserProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.User;
import ar.com.eccomerce.model.UserProduct;
import ar.com.eccomerce.sort.DescendingOrderUserProduct;

public class UserProductDAOImpl implements IUserProductDAO{
	private final String insertQuery = "insert into user_product(user_id, cod_prod) values (?,?)";
	private final String getAllQuery  = "select * from user_product";
	private final String showProductsOfUserQuery  =" select u.id, u.name, p.cod_prod, p.name from user_product up join user u on up.user_id = u.id "
			+ "join product p on up.cod_prod = p.cod_prod "
			+ "where up.user_id = ?";
	
	private Connection conn;
	
	public UserProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(UserProduct object) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setInt(1, object.getUserId());
		ps.setInt(2, object.getCodProd());
		
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public List<UserProduct> getAll() throws SQLException {
		Set<UserProduct> treeSetUserProduct = new TreeSet<UserProduct>(new DescendingOrderUserProduct());
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) 
			treeSetUserProduct.add(new UserProduct(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
		
		List<UserProduct> list = List.copyOf(treeSetUserProduct);
		rs.close();
		ps.close();
		return list;
	}

	@Override
	public String showAllProductsByUserId(User userId) throws SQLException, ObjectSQLNotExists {
		String txt = "";
		PreparedStatement ps = conn.prepareStatement(showProductsOfUserQuery);
		ps.setInt(1, userId.getId());
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		txt+="LIST PRODUCTS | ID: "+rs.getInt(1)+" | NAME: "+rs.getString(2)+"\n";
		txt+="ID PRODUCT: "+rs.getInt(3)+" NAME PRODUCT: "+rs.getString(4)+"\n";
		
		while(rs.next())
			txt+="ID PRODUCT: "+rs.getInt(3)+" NAME PRODUCT: "+rs.getString(4)+"\n";

		rs.close();
		ps.close();
		return txt;
	}

}
