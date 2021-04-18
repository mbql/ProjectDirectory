package com.xd.excel.enums;

public enum SexType {
	
	MALE("男"),
	FEMALE("女");

	private String typeName;

	SexType(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return typeName;
	}

}
