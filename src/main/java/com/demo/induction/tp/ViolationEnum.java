package com.demo.induction.tp;

/**
 * @author ashimjk on 12/18/2018
 */
public enum ViolationEnum {
	EMPTY_TYPE("TYPE", "provided type is empty"),
	INVALID_TYPE("TYPE", "provided type is invalid type (supported D or C)"),
	ZERO_AMOUNT("AMOUNT", "amount is should be greater than 0");

	private String property;
	private String desc;

	ViolationEnum(String property, String desc) {
		this.property = property;
		this.desc = desc;
	}

	public String getProperty() {
		return property;
	}

	public String getDesc() {
		return this.desc;
	}
}