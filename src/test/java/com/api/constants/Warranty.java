package com.api.constants;

public enum Warranty {

	
	IN_WARRANTY(1),
	OUT_WARRANTY(2);
	
	int code;
	
	Warranty(int code){
		this.code=code;
		
	}
	
	public int getcode() {
		return code;
	}
}
