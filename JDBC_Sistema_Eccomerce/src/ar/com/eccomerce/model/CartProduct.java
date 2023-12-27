package ar.com.eccomerce.model;

public class CartProduct {
	private Integer codCarr;
	private Integer codProd;
	
	
	public CartProduct(Integer codCarr) {
		super();
		this.codCarr = codCarr;
	}


	public CartProduct(Integer codCarr, Integer codProd) {
		super();
		this.codCarr = codCarr;
		this.codProd = codProd;
	}


	public Integer getCodCarr() {
		return codCarr;
	}


	public void setCodCarr(Integer codCarr) {
		this.codCarr = codCarr;
	}


	public Integer getCodProd() {
		return codProd;
	}


	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}


	@Override
	public String toString() {
		return "CartProduct [codCarr=" + codCarr + ", codProd=" + codProd + "]";
	}
	
	
	
}
