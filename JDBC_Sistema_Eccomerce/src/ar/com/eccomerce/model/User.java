package ar.com.eccomerce.model;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Double money;
	private Boolean isAdmin;
	private String mail;
	
	
	public User(Integer id) {
		super();
		this.id = id;
	}

	public User(Integer id, String name, String password, Double money, Boolean isAdmin, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.money = money;
		this.isAdmin = isAdmin;
		this.mail = mail;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
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
