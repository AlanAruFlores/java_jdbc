package ar.com.eccomerce.model;

public class Cart {
	private Integer codCarr;
	private Integer userId;
	private Float totalPrice;
	
	public Cart(Integer id, Boolean isByCartId) {
		super();
		if(isByCartId)
			this.codCarr = id; 
		else
			this.userId = id;
		
		
	}
	
	public Cart(Integer codCarr, Integer userId, Float totalPrice) {
		super();
		this.codCarr = codCarr;
		this.userId = userId;
		this.totalPrice = totalPrice;
	}

	public Integer getCodCarr() {
		return codCarr;
	}

	public void setCodCarr(Integer codCarr) {
		this.codCarr = codCarr;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Cart [codCarr=" + codCarr + ", userId=" + userId + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}
