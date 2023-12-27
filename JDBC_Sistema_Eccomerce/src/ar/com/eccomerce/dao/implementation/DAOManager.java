package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import ar.com.eccomerce.dao.ICartDAO;
import ar.com.eccomerce.dao.ICartProductDAO;
import ar.com.eccomerce.dao.ICommentDAO;
import ar.com.eccomerce.dao.IDAOManager;
import ar.com.eccomerce.dao.IPostDAO;
import ar.com.eccomerce.dao.IPostProductDAO;
import ar.com.eccomerce.dao.IProductDAO;
import ar.com.eccomerce.dao.IReportDAO;
import ar.com.eccomerce.dao.IUserDAO;
import ar.com.eccomerce.dao.IUserProductDAO;
import ar.com.eccomerce.dao.IUserStateDAO;

public class DAOManager implements IDAOManager{

	public final String URL_CONNECTION="jdbc:mysql://127.0.0.1:3306/sistema_eccomerce";
	private Connection conn;
	private CartDAOImpl cartDao;
	private CartProductDAOImpl cartProductDao;
	private CommentDAOImpl commentDao;
	private PostDAOImpl postDao;
	private PostProductDAOImpl postProductDao;
	private ProductDAOImpl productDao;
	private ReportDAOImpl reportDao;
	private UserDAOImpl userDao;
	private UserProductDAOImpl userProductDao;
	private UserStateDAOImpl userStateDao;
	
	
	public DAOManager() throws SQLException {
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "");
		this.conn = DriverManager.getConnection(URL_CONNECTION, properties);
	}
	
	@Override
	public ICartDAO getCartDAO() {
		if(this.cartDao == null)
			this.cartDao = new CartDAOImpl(conn);
		return this.cartDao;
	}

	@Override
	public ICartProductDAO getCartProductDAO() {
		if(this.cartProductDao == null)
			this.cartProductDao =  new CartProductDAOImpl(conn);
		return this.cartProductDao;
	}

	@Override
	public ICommentDAO getCommentDAO() {
		if(commentDao == null)
			this.commentDao = new CommentDAOImpl(conn);
		return commentDao;
	}

	@Override
	public IPostDAO getPostDAO() {
		if(this.postDao == null)
			this.postDao = new PostDAOImpl(conn);
		return this.postDao;
	}

	@Override
	public IPostProductDAO getPostProductDAO() {
		if(this.postProductDao == null)
			this.postProductDao = new PostProductDAOImpl(conn);
		return this.postProductDao;
	}
	
	@Override
	public IProductDAO getProductDAO() {
		if(this.productDao == null)
			this.productDao  = new ProductDAOImpl(conn);
		return this.productDao;
	}

	@Override
	public IReportDAO getReportDAO() {
		if(this.reportDao == null)
			this.reportDao = new ReportDAOImpl(conn);
		return this.reportDao;
	}

	@Override
	public IUserDAO getUserDAO() {
		if(this.userDao == null)
			this.userDao = new UserDAOImpl(conn);
		return this.userDao;
	}

	@Override
	public IUserProductDAO getUserProductDAO() {
		if(this.userProductDao == null)
			this.userProductDao = new UserProductDAOImpl(conn);
		return this.userProductDao;
	}

	@Override
	public IUserStateDAO getUserStateDAO() {
		if(this.userStateDao == null)
			this.userStateDao = new UserStateDAOImpl(conn);
		return this.userStateDao;
	}

	@Override
	public Boolean closeConnection() throws SQLException{
		if(conn.isClosed())
			return false;
		
		this.conn.close();
		return true;
	}
	
	@Override
	public Boolean verifyIfWeHaveConnection() throws SQLException{
		return !(this.conn.isClosed());
	}

}
