package com.emlakcepte.dto.realty.mapper;

import org.springframework.stereotype.Component;

import com.emlakcepte.dto.realty.request.RealtyRequest;
import com.emlakcepte.model.Realty;

@Component
public class RealtyMapper {
	
	public RealtyMapper() {}
	
	public Realty convert(RealtyRequest realtyRequest) {
		Realty realty = new Realty();
		realty.setNo(realtyRequest.getNo());
		realty.setTitle(realtyRequest.getTitle());
		realty.setProvince(realtyRequest.getProvince());
		realty.setDistrict(realtyRequest.getDistrict());
		realty.setUserId(realtyRequest.getUserId());
		realty.setStatus(realtyRequest.getStatus());
		realty.setCategoryType(realtyRequest.getCategoryType());
		realty.setPropertyType(realtyRequest.getPropertyType());
		return realty;
	}
}
