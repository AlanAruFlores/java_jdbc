package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.IPostDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Post;

public class PostDAOImpl implements IPostDAO {
	private Connection conn;
	public PostDAOImpl(Connection conn) {
		this.conn = conn;
	}
	@Override
	public Boolean insert(Post object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getById(Post object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Post newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Post object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptionOfPostById() throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}
	
}
