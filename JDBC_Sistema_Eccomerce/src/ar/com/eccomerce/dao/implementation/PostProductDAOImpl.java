package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

import ar.com.eccomerce.dao.IPostProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.PostProduct;
import ar.com.eccomerce.sort.AscendingOrderPostProduct;
import ar.com.eccomerce.sort.DescendingOrderPostProduct;

public class PostProductDAOImpl implements IPostProductDAO{
	
	private final String insertQuery = "insert into post_product (cod_post, cod_prod) values (?,?)";
	private final String getAllQuery = "select * from post_product";
	private final String getByIdQuery ="select * from post_product where cod_post = ? and cod_prod = ?";
	private final String deleteQuery = "delete from post_product where cod_post = ? and cod_prod = ?";
	private final String showDescription = "select ps.cod_post, ps.title, ps.description, ps.price, p.cod_prod, p.name, u.name from post_product pp join product p on pp.cod_prod = p.cod_prod "
			+ "join post ps on pp.cod_post = ps.cod_post "
			+ "join user u on ps.author_id = u.id "
			+ "where pp.cod_post = ?";
	
	private Connection conn;
	
	public PostProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(PostProduct object) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setInt(1, object.getCodPost());
		ps.setInt(2, object.getCodProd());
		
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public List<PostProduct> getAllAscending() throws SQLException {
		TreeSet<PostProduct> treeSetProducts = new TreeSet<PostProduct>(new AscendingOrderPostProduct());
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			PostProduct aux = new PostProduct(rs.getInt(1), rs.getInt(2));
			treeSetProducts.add(aux);
		}
		
		List<PostProduct> list = List.copyOf(treeSetProducts);
		
		rs.close();
		ps.close();
		return list;
	}
	
	@Override
	public List<PostProduct> getAllDescending() throws SQLException{
		TreeSet<PostProduct> treeSetProducts = new TreeSet<PostProduct>(new DescendingOrderPostProduct());
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			PostProduct aux = new PostProduct(rs.getInt(1), rs.getInt(2));
			treeSetProducts.add(aux);
		}
		
		List<PostProduct> list = List.copyOf(treeSetProducts);
		
		rs.close();
		ps.close();
		return list;
	}

	@Override
	public PostProduct getById(PostProduct object) throws SQLException, ObjectSQLNotExists {
		PostProduct postProduct = null;
		PreparedStatement ps =conn.prepareStatement(getByIdQuery);
		
		ps.setInt(1, object.getCodPost());
		ps.setInt(2, object.getCodProd());
		
		ResultSet rs = ps.executeQuery();
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		postProduct  = new PostProduct(rs.getInt(1), rs.getInt(2));
		rs.close();
		ps.close();
		return postProduct;
	}


	@Override
	public Boolean delete(PostProduct object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getCodPost());
		ps.setInt(2, object.getCodProd());
		
		var rows = ps.executeUpdate();
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
			
		}
		
		ps.close();
		return true;
	}

	@Override
	public String showPostDescritionById(PostProduct object) throws SQLException, ObjectSQLNotExists {
		String txt  = "";
		PreparedStatement ps = conn.prepareStatement(showDescription);
		ps.setInt(1,object.getCodPost());
		
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		

		txt+="ID: "+rs.getInt(1)+"\n";
		txt+="________"+rs.getString(2)+"________\n";
		txt+=""+rs.getString(3)+"\n";
		txt+="PRICE $"+rs.getFloat(4)+"\n";
		txt+="WRITTEN BY: "+rs.getString(7)+"\n\n";		
		txt+="PRODUCTS :\n";
		txt+="ID: "+rs.getInt(5)+" | "+rs.getString(6)+"\n";
		while(rs.next())
			txt+="ID: "+rs.getInt(5)+" | "+rs.getString(6)+"\n";
		return txt;
	}

}
