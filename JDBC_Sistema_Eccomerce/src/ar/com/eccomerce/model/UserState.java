package ar.com.eccomerce.model;

public class UserState {
	private Integer codSta;
	private String name;
	
	public UserState(Integer codSta) {
		super();
		this.codSta = codSta;
	}
	
	public UserState(String name) {
		this.name = name;
	}

	public UserState(Integer codSta, String name) {
		super();
		this.codSta = codSta;
		this.name = name;
	}


	public Integer getCodSta() {
		return codSta;
	}


	public void setCodSta(Integer codSta) {
		this.codSta = codSta;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "UserState [codSta=" + codSta + ", name=" + name + "]";
	}
	
	

	
	
}
