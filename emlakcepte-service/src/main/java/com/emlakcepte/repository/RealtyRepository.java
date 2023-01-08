package com.emlakcepte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emlakcepte.model.Realty;
import com.emlakcepte.model.enums.RealtyType;

public interface RealtyRepository extends JpaRepository<Realty, Integer> {

//	List<Realty> findByProvince(String province); 

	List<Realty> findByStatus(RealtyType status);
	
	@Query(value="UPDATE realty SET status = 'ACTIVE' WHERE id = :id",nativeQuery =true)
	List<Realty> setActiveAllReviewRealty(@Param("id") Integer id);
	
	@Query(value="select * from Realty where province in (:provinces)",nativeQuery =true)
	List<Realty> findByProvince(@Param("provinces") List<String> provinces);
	
	@Query(value="select * from Realty where province in (:provinces) and district in (:districts)",nativeQuery =true)
	List<Realty> findByProvinceAndDistrict(@Param("provinces") List<String> provinces, @Param("districts") List<String> districts);
	
	@Query(value="select * from Realty where province in (:provinces) and status = :status",nativeQuery =true)
	List<Realty> findByStatusAndProvince(@Param("provinces") List<String> provinces,@Param("status") RealtyType realtyType);
	
	@Query(value="select * from Realty where province in (:provinces) and property_type = :type",nativeQuery =true)
	List<Realty> findByPropertyType(@Param("provinces") List<String> provinces,@Param("type") String type);

}
