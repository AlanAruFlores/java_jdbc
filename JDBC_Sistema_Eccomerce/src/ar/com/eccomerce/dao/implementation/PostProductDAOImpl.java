package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.IPostProductDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.PostProduct;

public class PostProductDAOImpl implements IPostProductDAO{
	private Connection conn;
	
	public PostProductDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Boolean insert(PostProduct object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostProduct> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostProduct getById(PostProduct object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(PostProduct newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(PostProduct object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showPostDescritionById(PostProduct object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
