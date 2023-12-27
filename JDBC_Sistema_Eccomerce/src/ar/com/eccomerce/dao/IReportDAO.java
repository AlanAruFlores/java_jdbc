package ar.com.eccomerce.dao;

import java.sql.SQLException;
import java.util.List;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Report;
import ar.com.eccomerce.model.User;

public interface IReportDAO {	
	public Boolean insert(Report object) throws SQLException;
	public List<Report> getAll() throws SQLException;
	public Report getById(Report object) throws SQLException,ObjectSQLNotExists;
	public String seeAllReportsByReceptorId(User receptor) throws SQLException , ObjectSQLNotExists;
	public Boolean delete(Report object) throws SQLException ,ObjectSQLNotExists;

}
