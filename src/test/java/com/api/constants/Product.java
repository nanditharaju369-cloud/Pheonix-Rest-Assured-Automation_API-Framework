package com.api.constants;

//to remove Magic Numbers
public enum Product {
	
	NEXUS_2(1),PIXEL(2);
	
	int code;
	private Product (int code) {
		this.code=code;
	}
	
	public int getcode() {
		return code;
	}
	

}
