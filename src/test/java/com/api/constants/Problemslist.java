package com.api.constants;

public enum Problemslist {

	SMARTPHONE_IS_RUNNING_SLOW(1), 
	POOR_BATTTERY_LIFE(2),
	PHONE_OR_APP_CRASHES(3),
	SYNC_ISSUE(4),
	OVERHEATING(5);
	
	int code;
	
	Problemslist(int code){
		this.code=code;
	}
	
	public int getcode() {
		return code;
	}
}
