package ar.com.eccomerce.dao;

import java.sql.SQLException;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.PostProduct;

public interface IPostProductDAO extends DAO<PostProduct>{
	public String showPostDescritionById(PostProduct object) throws SQLException, ObjectSQLNotExists;
	
}
