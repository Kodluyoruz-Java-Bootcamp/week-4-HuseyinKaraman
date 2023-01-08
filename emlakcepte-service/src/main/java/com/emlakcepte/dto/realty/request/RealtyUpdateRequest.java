package com.emlakcepte.dto.realty.request;

import com.emlakcepte.model.enums.RealtyType;

public class RealtyUpdateRequest {

	private Integer id;
	private Integer userId;
	private RealtyType status;
	
	public RealtyUpdateRequest() {}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public RealtyType getStatus() {
		return status;
	}

	public void setStatus(RealtyType status) {
		this.status = status;
	}
	
	
}
