package com.emlakcepte.client.model;

public class Banner {

	private Integer ilanNo;
	private Integer adet;
	private String telNo1;
	private String telNo2;
	// adres bilgilerinin eklenmesi

	public Banner(Integer ilanNo, Integer adet, String telNo1, String telNo2) {
		this.ilanNo = ilanNo;
		this.adet = adet;
		this.telNo1 = telNo1;
		this.telNo2 = telNo2;
	}

	public Integer getIlanNo() {
		return ilanNo;
	}

	public void setIlanNo(Integer ilanNo) {
		this.ilanNo = ilanNo;
	}

	public Integer getAdet() {
		return adet;
	}

	public void setAdet(Integer adet) {
		this.adet = adet;
	}

	public String getTelNo1() {
		return telNo1;
	}

	public void setTelNo1(String telNo1) {
		this.telNo1 = telNo1;
	}

	public String getTelNo2() {
		return telNo2;
	}

	public void setTelNo2(String telNo2) {
		this.telNo2 = telNo2;
	}

}
