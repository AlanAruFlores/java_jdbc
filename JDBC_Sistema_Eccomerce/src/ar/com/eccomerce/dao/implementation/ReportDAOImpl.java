package ar.com.eccomerce.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.eccomerce.dao.IReportDAO;
import ar.com.eccomerce.exceptions.ObjectSQLNotExists;
import ar.com.eccomerce.model.Report;
import ar.com.eccomerce.model.User;

public class ReportDAOImpl implements IReportDAO {
	private Connection conn;
	private final String insertQuery = "insert into reports(user_receptor, user_emisor) values (?,?)";
	private final String getAllQuery = "select * from reports";
	private final String getByIdQuery = "select * from reports where user_receptor = ? and user_emisor = ?";
	private final String deleteQuery  = "delete from reports where user_receptor = ? and user_emisor = ?";
	private final String seeAllReport =" select rec.name , emi.name from reports r join user rec on r.user_receptor = rec.id "
			+ "join user emi on r.user_emisor = emi.id where rec.id = ?";
	
	public ReportDAOImpl(Connection conn) {
		this.conn  = conn;
		
	}
	
	
	@Override
	public Boolean insert(Report object) throws SQLException {
		PreparedStatement ps =conn.prepareStatement(insertQuery);
		ps.setInt(1, object.getUserReceptorId());
		ps.setInt(2, object.getUserEmisorId());
		
		ps.executeUpdate();
		ps.close();
		return true;
	}

	@Override
	public List<Report> getAll() throws SQLException {
		List<Report> list = new ArrayList<>();
		PreparedStatement ps = conn.prepareStatement(getAllQuery);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Report aux = new Report(rs.getInt(1), rs.getInt(2));
			list.add(aux);
		}
		rs.close();
		ps.close();
		return list;
	}

	@Override
	public Report getById(Report object) throws SQLException, ObjectSQLNotExists {
		Report report = null;
		PreparedStatement ps = conn.prepareStatement(getByIdQuery);
		ps.setInt(1, object.getUserReceptorId());
		ps.setInt(2, object.getUserEmisorId());
		
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		report = new Report(rs.getInt(1), rs.getInt(2));
		rs.close();
		ps.close();
		return report;
	}

	@Override
	public Boolean delete(Report object) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(deleteQuery);
		ps.setInt(1, object.getUserReceptorId());
		ps.setInt(2, object.getUserEmisorId());
	
		var rows = ps.executeUpdate();
		
		if(rows == 0) {
			ps.close();
			throw new ObjectSQLNotExists();
		}
		ps.close();
		return true;
	}

	@Override
	public String seeAllReportsByReceptorId(User receptor) throws SQLException, ObjectSQLNotExists {
		PreparedStatement ps = conn.prepareStatement(seeAllReport);
		ps.setInt(1, receptor.getId());
		
		String txt = "";
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) {
			rs.close();
			ps.close();
			throw new ObjectSQLNotExists();
		}
		
		Integer cont = 1;
		
		txt += "HISTORIAL REPORTS OF THE USER "+rs.getString(1)+"\n";
		txt += cont+")"+rs.getString(2)+"\n";
		while(rs.next()) {
			cont++;
			txt += cont+")"+rs.getString(2)+"\n";
		}
		txt+="TOTAL REPORTS: "+cont+"\n";
		return txt;
	}

}
