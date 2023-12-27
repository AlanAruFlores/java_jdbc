package ar.com.eccomerce.model;

public class Product {
	private Integer cod_prod;
	private String name;
	
	
	public Product(Integer cod_prod) {
		super();
		this.cod_prod = cod_prod;
	}


	public Product(Integer cod_prod, String name) {
		super();
		this.cod_prod = cod_prod;
		this.name = name;
	}


	@Override
	public String toString() {
		return "Product [cod_prod=" + cod_prod + ", name=" + name + "]";
	}
	
	
	
}
