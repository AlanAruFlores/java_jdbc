package ar.com.eccomerce.sort;

import java.util.Comparator;

import ar.com.eccomerce.model.UserProduct;

public class DescendingOrderUserProduct implements Comparator<UserProduct>{

	@Override
	public int compare(UserProduct o1, UserProduct o2) {
		return o1.getId().compareTo(o2.getId()) * -1;
	}
	
}
