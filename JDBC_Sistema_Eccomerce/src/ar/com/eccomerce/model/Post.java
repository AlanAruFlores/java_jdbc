package ar.com.eccomerce.model;

public class Post {
	private Integer codPost;
	private String title;
	private String description;
	private Float price;
	private Integer authorId;
	
	
	public Post(Integer id, Boolean isByAutor) {
		super();
		if(isByAutor)
			this.authorId = id;
		else
			this.codPost = id;
	}


	public Post(String title, String description, Float price, Integer authorId) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.authorId = authorId;
	}


	public Post(Integer codPost, String title, String description, Float price, Integer authorId) {
		super();
		this.codPost = codPost;
		this.title = title;
		this.description = description;
		this.price = price;
		this.authorId = authorId;
	}


	public Integer getCodPost() {
		return codPost;
	}


	public void setCodPost(Integer codPost) {
		this.codPost = codPost;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public Integer getAuthorId() {
		return authorId;
	}


	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}


	@Override
	public String toString() {
		return "Post [codPost=" + codPost + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", authorId=" + authorId + "]";
	}
	
}
