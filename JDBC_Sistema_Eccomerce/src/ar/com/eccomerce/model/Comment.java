package ar.com.eccomerce.model;

public class Comment {
	private Integer codCom;
	private String message;
	private Integer userId;
	private Integer codPost; 
	
	public Comment(Integer codPost) {
		super();
		this.codPost = codPost;
	}

	public Comment(Integer codCom, String message,Integer userId,Integer codPost) {
		super();
		this.codCom = codCom;
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
