package com.emlakcepte.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.client.BannerServiceClient;
import com.emlakcepte.client.model.Banner;
import com.emlakcepte.dto.realty.mapper.RealtyMapper;
import com.emlakcepte.dto.realty.request.RealtyRequest;
import com.emlakcepte.dto.realty.request.RealtyUpdateRequest;
import com.emlakcepte.model.Realty;
import com.emlakcepte.model.User;
import com.emlakcepte.model.enums.RealtyType;
import com.emlakcepte.repository.RealtyRepository;

@Service
public class RealtyService {

	@Autowired
	private RealtyRepository realtyRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BannerServiceClient bannerServiceClient;
	
	@Autowired
	private RealtyMapper realtyMapper;
	

	/** @Note:Oluşturulan realty kayıt edilir */
	public boolean create(RealtyRequest realtyRequest) {
		User user = userService.getById(realtyRequest.getUserId());
		// girilen Id'ye ait User bulunamaz veya kullanıcı giriş yapmamışsa null  donecek!
		if (!userService.checkUser(user)) return false;
		
		if (user.getPackages() > 0) { // eger kullanacagımız paket yoksa ilan veremeyecegiz!
			Realty realty = realtyMapper.convert(realtyRequest);
			user.setPackages(user.getPackages()-1);
			realty.setUser(user);
			/** Note: Banner-service */
			Banner bannerRequest = new Banner(realty.getNo(), 1, "123456789", "021546876");
			bannerServiceClient.create(bannerRequest);
			System.err.println(user);
			return Objects.nonNull(userService.updateUser(user));			
		}
		return false;
	}
	
	/** @Note:Oluşturulan realty 'nin status'u guncellenir */
	public boolean updateStatus(RealtyUpdateRequest updateRequest) {
		User byId = userService.getById(updateRequest.getUserId());
		Realty realty = getById(updateRequest.getId());
		
		/** @Note: girilen Id'ye ait User bulunamaz veya kullanıcı giriş yapmamışsa 
		 * veya id'e ait realty bulunmazsa  null donecek! */
		if (!userService.checkUser(byId) || checkRealty(realty)) return false;
		if (RealtyType.IN_REVIEW.equals(updateRequest.getStatus())) return false;
		
		realty.setStatus(updateRequest.getStatus());
		
		return Objects.nonNull(realtyRepository.save(realty));
	}
	
	
	/** @Note: Id'si girilen realty silinir */
	public boolean deleteRealty(Integer realtyId) {
		Realty realty = getById(realtyId);
		if (checkRealty(realty))
			return false; // Id'ye ait realty varmı ??
		else { // User giriş yapmışmı?
			if (!userService.checkUser(realty.getUser()))
				return false;
		}

		realtyRepository.deleteById(realtyId);
		return true;
	}

	/** @Note: butun realty'leri verir */
	public List<Realty> getAll() {
		return realtyRepository.findAll();
	}

	/** @Note: Girilen realtyId'sine ait Realty'i doner */
	public Realty getById(Integer realtyId) {
		Optional<Realty> byId = realtyRepository.findById(realtyId);
		return byId.isPresent() ? byId.get() : null;
	}

	/** @Note: girilen duruma göre aktif veya pasif ilanları verir */
	public List<Realty> getByStatus(String status) {
		if (!status.equalsIgnoreCase("active") && !status.equalsIgnoreCase("passive")) {
			return null;
		}
		return realtyRepository.findByStatus(RealtyType.valueOf(status));
	}

	/** @Note: girilen il veya iller'de bulunan aktif veya pasif ilanları verir */
	public List<Realty> getByStatusAndProvince(List<String> province, String status) {
		if (!status.equals("active") && !status.equals("passive")) {
			return null;
		}
		return realtyRepository.findByStatusAndProvince(province, RealtyType.valueOf(status));
	}

	/** * @Note: Girilen il veya iller 'de bulunan ilanları verir */
	public List<Realty> getAllRealtyByProvince(List<String> province) {
		return realtyRepository.findByProvince(province);
	}

	/** * @Note: girilen il ve ilçeye göre bulunan Realty'leri verir */
	public List<Realty> getByProvinceAndDistrict(List<String> province, List<String> district) {
		return realtyRepository.findByProvinceAndDistrict(province, district);
	}

	/** @Note: girilen realtyId'e sahip realty'nin süresi istenilen ay kadar uzatılır.
	 * eger kullanıcı online ve istenilen sayı kadar paket'e sahipse */
	public boolean extendDate(Integer realtyId, Integer extendMonth) {
		Realty realty = getById(realtyId);
		if (checkRealty(realty)) return false;
		User user = realty.getUser();
		// user online mi? ve uzatılmak istenilen paket sayısına sahipmiyiz ??
		if (!userService.checkUser(user) || user.getPackages()<extendMonth) return false; 
		realty.setDueDate(realty.getDueDate().plusDays(extendMonth));
		user.setPackages(user.getPackages()-extendMonth);
		userService.updateUser(user);
		return true;
	}
	
	/**  @Note: null kontrolü yapar*/
	private boolean checkRealty(Realty realty) {
		return Objects.isNull(realty);
	}

}
