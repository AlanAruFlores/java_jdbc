package ar.com.eccomerce.model;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Float money;
	private Boolean isAdmin;
	private String mail;
	private Integer codSta;
	
	
	public User(Integer id) {
		super();
		this.id = id;
	}

	
	public User(String name, String password, Float money, Boolean isAdmin, String mail, Integer codSta) {
		super();
		this.name = name;
		this.password = password;
		this.money = money;
		this.isAdmin = isAdmin;
		this.mail = mail;
		this.codSta = codSta;
	}


	public User(Integer id, String name, String password, Float money, Boolean isAdmin, String mail, Integer codSta) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.money = money;
		this.isAdmin = isAdmin;
		this.mail = mail;
		this.codSta = codSta;
	}

	public Integer getCodSta() {
		return this.codSta;
	}
	
	public void setCodSta(Integer newCod) {
		this.codSta = newCod;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", money=" + money + ", isAdmin="
				+ isAdmin + ", mail=" + mail + "]";
	}
	
}
