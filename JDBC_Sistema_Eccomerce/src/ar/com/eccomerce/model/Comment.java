package ar.com.eccomerce.model;

public class Comment {
	private Integer codCom;
	private String message;
	private Integer userId;
	private Integer codPost; 
	
	public Comment() {}
	
	public Comment(Integer codCom) {
		super();
		this.codCom = codCom;
	}

	public Comment(Integer codPost, String message, Integer userId) {
		super();
		this.codPost = codPost;
		this.message = message;
		this.userId = userId;
	}

	public Comment(Integer codCom, Integer codPost, String message,Integer userId) {
		super();
		this.codCom = codCom;
		this.codPost = codPost;
		this.message = message;
		this.userId = userId;
	}

	public Integer getCodCom() {
		return codCom;
	}

	public void setCodCom(Integer codCom) {
		this.codCom = codCom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getCodPost() {
		return this.codPost;
	}
	
	public void setCodPost(Integer cod) {
		this.codPost = cod;
	}

	@Override
	public String toString() {
		return "Comment [codCom=" + codCom + ", message=" + message + ", userId=" + userId + ", codPost=" + codPost
				+ "]";
	}
	
	
	
	
		
	
}
