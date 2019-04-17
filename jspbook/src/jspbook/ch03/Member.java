package jspbook.ch03;

import java.util.Objects;

public class Member {
	private int id;
	private String name;
	private String eMail;
	private String pNum;
	
	//검증코드 필요
//	public void setId(String id) {
//		this.id = id;
//	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public void setpNum(String pNum) {
		this.pNum = pNum;
	}
	
	
	public int getId() {
		if (pNum!=null) {
			id = Objects.hash(name, eMail, pNum);
			System.out.println(toString());
		}
		return id;
	}
	
//	public int getNewId() {
//		if (pNum!=null) {
//			id = Objects.hash(name, eMail, pNum);
//			System.out.println(toString());
//		}
//		return id;
//	}
	
	public String getName() {
		return name;
	}
	public String geteMail() {
		return eMail;
	}
	public String getpNum() {
		return pNum;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", eMail=" + eMail + ", pNum=" + pNum + "]";
	}
	
}
