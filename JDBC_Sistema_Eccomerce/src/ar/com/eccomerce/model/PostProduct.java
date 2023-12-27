package ar.com.eccomerce.model;

public class PostProduct {
	private Integer codPost;
	private Integer codProd;
	
	public PostProduct(Integer codPost) {
		super();
		this.codPost = codPost;
	}

	public PostProduct(Integer codPost, Integer codProd) {
		super();
		this.codPost = codPost;
		this.codProd = codProd;
	}
	
	public Integer getCodPost() {
		return codPost;
	}
	public void setCodPost(Integer codPost) {
		this.codPost = codPost;
	}
	public Integer getCodProd() {
		return codProd;
	}
	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}

	@Override
	public String toString() {
		return "PostProduct [codPost=" + codPost + ", codProd=" + codProd + "]";
	}
	
	
}
