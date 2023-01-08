package com.emlakcepte.dto.realty.request;

import com.emlakcepte.model.enums.PropertyType;
import com.emlakcepte.model.enums.RealtyType;
import com.emlakcepte.model.enums.SalesType;

public class RealtyRequest {
	
	private Integer no;
	private Integer userId;
	private String title;
	private String province;
	private String district;
	private RealtyType status;
	private SalesType categoryType;
	private PropertyType propertyType;
	
	public RealtyRequest() {}
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public RealtyType getStatus() {
		return status;
	}
	public void setStatus(RealtyType status) {
		this.status = status;
	}
	public SalesType getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(SalesType categoryType) {
		this.categoryType = categoryType;
	}
	public PropertyType getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	
	
	
	
}
