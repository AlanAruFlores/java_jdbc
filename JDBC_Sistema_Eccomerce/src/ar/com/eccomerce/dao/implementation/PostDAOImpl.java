package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eccomerce.dao.IPostDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Post;

public class PostDAOImpl implements IPostDAO {
	private final String insertQuery = "insert into post (title,description,price,author_id) values (?,?,?,?) ";
	private final String getAllQuery = "select * from post";
	private final String getByIdQuery = "select * from post where cod_post = ?";
	private final String updateQuery = "update post set title = ? , description = ? , price = ?, author_id = ? where cod_post = ?";
	private final String deleteQuery = "delete from post where cod_post = ?";
	private final String getDescriptionOfPostByIdQuery = "select p.cod_post, p.title, p.description, p.price, u.name from post p join user u on p.author_id = u.id "
			+ "where p.author_id = ?";
	private Connection conn;
	public PostDAOImpl(Connection conn) {
		this.conn = conn;
	}
	@Override
	public Boolean insert(Post object) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setString(1,object.getTitle());
		ps.setString(2, object.getDescription());
		ps.setFloat(3, object.getPrice());
		ps.setInt(4, object.getAuthorId());
		
		ps.executeUpdate();
		
		ps.close();
		
		return true;
	}

	@Override
	public List<Post> getAll() throws SQLException {
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		List<Post> listPosts = new ArrayList<>();
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Post aux = new Post(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getFloat(4), rs.getInt(5));
			listPosts.add(aux);
		}
		
		rs.close();
		ps.close();
		
		return listPosts;
	}

	@Override
	public Post getById(Post object) throws SQLException, ObjectSQLNotExists {
		Post post = null;
		PreparedStatement ps = conn.prepareStatement(getByIdQuery);
		ps.setInt(1, object.getCodPost());
		
		ResultSet rs = ps.executeQuery();
		if(!rs.next()) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		post = new Post(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getFloat(4), rs.getInt(5));
		ps.close();
		return post;
	}

	@Override
	public Boolean update(Post newData) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(updateQuery);
		ps.setString(1,newData.getTitle());
		ps.setString(2, newData.getDescription());
		ps.setFloat(3, newData.getPrice());
		ps.setInt(4, newData.getAuthorId());
		ps.setInt(5, newData.getCodPost());
		
		var rows =ps.executeUpdate();
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		ps.close();
		return true;
	}

	@Override
	public Boolean delete(Post object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getCodPost());
		
		var rows =ps.executeUpdate();
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		ps.close();
		return true;
	}

	@Override
	public String getDescriptionOfPostById(Post object) throws SQLException, ObjectSQLNotExists {
		String txt = "";
		PreparedStatement ps = conn.prepareStatement(getDescriptionOfPostByIdQuery);
		ps.setInt(1, object.getCodPost());
		
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
		txt+="WRITTEN BY: "+rs.getString(5)+"\n\n";
		rs.close();
		ps.close();
		return txt;
	}
	
}
