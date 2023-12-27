package ar.com.eccomerce.dao;

import java.sql.SQLException;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Post;

public interface IPostDAO extends DAO<Post>{
	public String getDescriptionOfPostById() throws SQLException , ObjectSQLNotExists;
}
