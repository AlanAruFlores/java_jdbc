package ar.com.eccomerce.sort;

import java.util.Comparator;

import ar.com.eccomerce.model.PostProduct;

public class DescendingOrderPostProduct implements Comparator<PostProduct>{

	@Override
	public int compare(PostProduct o1, PostProduct o2) {
		if(o1.getCodPost().compareTo(o2.getCodPost()) * -1 != 0)
			return o1.getCodPost().compareTo(o2.getCodPost()) * -1;
		
		return o1.getCodProd().compareTo(o2.getCodProd()) * -1;
	}

}
