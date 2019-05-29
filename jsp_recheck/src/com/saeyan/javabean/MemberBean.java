package com.saeyan.javabean;

public class MemberBean {
	private String name;
	private String userId;
	
	public MemberBean() {
		super();
	}

	public MemberBean(String name, String userId) {
		super();
		this.name = name;
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "MemberBean [name=" + name + ", userId=" + userId + "]";
	}
	
	
	
	
}
