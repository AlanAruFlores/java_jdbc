package ar.com.eccomerce.model;

public class Report {
	private Integer userReceptorId;
	private Integer userEmisorId;
	
	public Report(Integer userReceptorId) {
		super();
		this.userReceptorId = userReceptorId;
	} 
	
	public Report(Integer userReceptorId, Integer userEmisorId) {
		super();
		this.userReceptorId = userReceptorId;
		this.userEmisorId = userEmisorId;
	}
	
	public Integer getUserReceptorId() {
		return userReceptorId;
	}
	public void setUserReceptorId(Integer userReceptorId) {
		this.userReceptorId = userReceptorId;
	}
	public Integer getUserEmisorId() {
		return userEmisorId;
	}
	public void setUserEmisorId(Integer userEmisorId) {
		this.userEmisorId = userEmisorId;
	}
	@Override
	public String toString() {
		return "Report [userReceptorId=" + userReceptorId + ", userEmisorId=" + userEmisorId + "]";
	}
	
	
}
