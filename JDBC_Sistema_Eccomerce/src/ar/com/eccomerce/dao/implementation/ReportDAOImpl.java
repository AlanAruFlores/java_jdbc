package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.dao.IReportDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Report;
import ar.com.eccomerce.model.User;

public class ReportDAOImpl implements IReportDAO {
	private Connection conn;
	
	public ReportDAOImpl(Connection conn) {
		this.conn  = conn;
		
	}
	
	
	@Override
	public Boolean insert(Report object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report getById(Report object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Report newData) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Report object) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String seeAllReportsByReceptorId(User receptor) throws SQLException, ObjectSQLNotExists {
		// TODO Auto-generated method stub
		return null;
	}

}
