package ar.com.eccomerce.model;

public class Product {
	private Integer cod_prod;
	private String name;
	
	
	public Product(Integer cod_prod) {
		super();
		this.cod_prod = cod_prod;
	}


	public Product(String name) {
		super();
		this.name = name;
	}


	public Product(Integer cod_prod, String name) {
		super();
		this.cod_prod = cod_prod;
		this.name = name;
	}
	
	

	public Integer getCod_prod() {
		return cod_prod;
	}


	public void setCod_prod(Integer cod_prod) {
		this.cod_prod = cod_prod;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Product [cod_prod=" + cod_prod + ", name=" + name + "]";
	}
	
	
	
}
