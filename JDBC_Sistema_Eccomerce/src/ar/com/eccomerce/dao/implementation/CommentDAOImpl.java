package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eccomerce.dao.ICommentDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Comment;

public class CommentDAOImpl implements ICommentDAO{
	private final String insertQuery = "insert into comment (cod_post,message, user_id) values (?,?,?)";
	private final String getAllQuery = "select * from comment";
	private final String getByIdQuery = "select * from comment where cod_com = ?";
	private final String updateQuery = "update comment set message = ? where cod_com =? and cod_post = ?";
	private final String deleteQuery = "delete from comment where cod_com = ? and cod_post =?";
	
	
	private Connection conn;
	
	public CommentDAOImpl (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(Comment object) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setInt(1, object.getCodPost());
		ps.setString(2, object.getMessage());
		ps.setInt(3, object.getUserId());
	
		ps.executeUpdate();
		ps.close();
		
		return true;
	}

	@Override
	public List<Comment> getAll() throws SQLException {
		List<Comment> list = new ArrayList<Comment>();
		PreparedStatement ps  = conn.prepareStatement(getAllQuery);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Comment aux = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getInt(4));
			list.add(aux);
		}
		rs.close();
		ps.close();
		return list;
	}

	@Override
	public Comment getById(Comment object) throws SQLException, ObjectSQLNotExists {
		Comment com = null;
		PreparedStatement ps = conn.prepareStatement(getByIdQuery);
		ps.setInt(1, object.getCodCom());
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		com = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getInt(4));
		
		rs.close();
		ps.close();
		
		return com;
	}

	@Override
	public Boolean update(Comment newData) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(updateQuery);
		ps.setString(1, newData.getMessage());
		ps.setInt(2, newData.getCodCom());
		ps.setInt(3, newData.getCodPost());

		var rows = ps.executeUpdate();
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		ps.close();
		return true;
	}

	@Override
	public Boolean delete(Comment object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getCodCom());
		ps.setInt(2, object.getCodPost());
		
		var rows = ps.executeUpdate();
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		ps.close();
		return true;
	}

}
