package ar.com.eccomerce.dao;

import java.sql.SQLException;

public interface IDAOManager {
	public ICartDAO getCartDAO();
	public ICartProductDAO getCartProductDAO();
	public ICommentDAO getCommentDAO();
	public IPostDAO getPostDAO();
	public IPostProductDAO getPostProductDAO();
	public IProductDAO getProductDAO();
	public IReportDAO getReportDAO();
	public IUserDAO getUserDAO();
	public IUserProductDAO getUserProductDAO();
	public IUserStateDAO getUserStateDAO();
	public Boolean closeConnection()throws SQLException;
	public Boolean verifyIfWeHaveConnection() throws SQLException;
}
