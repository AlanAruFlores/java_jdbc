package ar.com.eccomerce.model;

public class UserProduct {
	private Integer id;
	private Integer userId;
	private Integer codProd;
	
	public UserProduct(Integer userId) {
		super();
		this.userId = userId;
	}
	
	public UserProduct(Integer userId, Integer codProd) {
		super();
		this.userId = userId;
		this.codProd = codProd;
	}
	
	public UserProduct(Integer id, Integer userId, Integer codProd) {
		super();
		this.id = id;
		this.userId = userId;
		this.codProd = codProd;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCodProd() {
		return codProd;
	}
	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}

	@Override
	public String toString() {
		return "UserProduct [userId=" + userId + ", codProd=" + codProd + "]";
	}
	
	
}
