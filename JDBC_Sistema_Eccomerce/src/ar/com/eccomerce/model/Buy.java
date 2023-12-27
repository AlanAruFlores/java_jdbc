package ar.com.eccomerce.model;

public class Buy {
	private Integer userId;
	private Integer codPost;
	
	
	public Buy(Integer userId) {
		super();
		this.userId = userId;
	}


	public Buy(Integer userId, Integer codPost) {
		super();
		this.userId = userId;
		this.codPost = codPost;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getCodPost() {
		return codPost;
	}


	public void setCodPost(Integer codPost) {
		this.codPost = codPost;
	}


	@Override
	public String toString() {
		return "Buy [userId=" + userId + ", codPost=" + codPost + "]";
	}
	
	
	
	
}
