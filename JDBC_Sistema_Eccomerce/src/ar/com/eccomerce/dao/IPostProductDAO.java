package ar.com.eccomerce.dao;

import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.PostProduct;

public interface IPostProductDAO{
	public Boolean insert(PostProduct object) throws SQLException;
	public List<PostProduct> getAllAscending() throws SQLException;
	public List<PostProduct> getAllDescending() throws SQLException;
	public PostProduct getById(PostProduct object) throws SQLException,ObjectSQLNotExists;
	public String showPostDescritionById(PostProduct object) throws SQLException, ObjectSQLNotExists;
	public Boolean delete(PostProduct object) throws SQLException ,ObjectSQLNotExists;

}
