package ar.com.eccomerce.dao;

import java.sql.SQLException;

import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Report;
import ar.com.eccomerce.model.User;

public interface IReportDAO extends DAO<Report> {	
	public String seeAllReportsByReceptorId(User receptor) throws SQLException , ObjectSQLNotExists;
}
