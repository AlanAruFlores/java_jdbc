package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eccomerce.dao.IProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Product;

public class ProductDAOImpl implements IProductDAO{
	private Connection conn;
	private final String insertQuery = "insert into product(name) values (?)";
	private final String getAllQuery = "select * from product";
	private final String getByIdQuery = "select * from product where cod_prod = ?";
	private final String updateQuery = "update product set name = ? where cod_prod = ?";
	private final String deleteQuery = "delete from product where cod_prod = ?";
	
	public ProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public Boolean insert(Product object) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setString(1, object.getName());
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public List<Product> getAll() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		ResultSet rs = ps.executeQuery();
		
		List<Product> list = new ArrayList<>();
		
		while(rs.next()) {
			Product aux = new Product(rs.getInt(1),rs.getString(2));
			list.add(aux);
		}
		
		rs.close();
		ps.close();
		return list;
	}

	@Override
	public Product getById(Product object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(getByIdQuery);
		ps.setInt(1, object.getCod_prod());
		ResultSet rs = ps.executeQuery();
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		Product product = new Product (rs.getInt(1), rs.getString(2));
		return product;
	}

	@Override
	public Boolean update(Product newData) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(updateQuery);
		ps.setString(1, newData.getName());
		ps.setInt(2, newData.getCod_prod());
			
		var rows = ps.executeUpdate();
		
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		ps.close();
		return true;
	}

	@Override
	public Boolean delete(Product object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getCod_prod());
		
		var rows = ps.executeUpdate();
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		ps.close();
		return true;	
	}

}
