package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.ICommentDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Comment;

public class CommentDAOImpl implements ICommentDAO{
	private Connection conn;
	
	public CommentDAOImpl (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(Comment object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getById(Comment object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Comment newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Comment object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
