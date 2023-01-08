package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.dto.realty.request.RealtyRequest;
import com.emlakcepte.dto.realty.request.RealtyUpdateRequest;
import com.emlakcepte.model.Realty;
import com.emlakcepte.service.RealtyService;

@RestController
@RequestMapping(value = "/realtyes")
public class RealtyController {

	@Autowired
	private RealtyService realtyService;

	/** @Note : kayıtlı ilanları verir */
	@GetMapping
	public List<Realty> getAll() {
		return realtyService.getAll();
	}

	/** @Note : Realty oluşturmamızı saglar veya RealtyId 'ye sahip olan Realty guncellenir!
	 *  userId girilmeli!
	 */
	@PostMapping
	public ResponseEntity<HttpStatus> create(@RequestBody RealtyRequest realtyRequest) {
		return new ResponseEntity<>(
				realtyService.create(realtyRequest) ? HttpStatus.CREATED : HttpStatus.UNAUTHORIZED);
	}

	/** @Note : Realty oluşturmamızı saglar veya RealtyId 'ye sahip olan Realty guncellenir!
	 *  userId girilmeli!
	 */
	@PutMapping
	public ResponseEntity<HttpStatus> updateStatus(@RequestBody RealtyUpdateRequest updateRequest) {
		return new ResponseEntity<>(
				realtyService.updateStatus(updateRequest) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
	}
	
	/** @Note : girilen id'e ait ilanları verir */
	@GetMapping(value = "/{realtyId}")
	public ResponseEntity<Realty> getById(@PathVariable Integer realtyId) {
		return new ResponseEntity<>(realtyService.getById(realtyId),HttpStatus.OK);
	}
	
	/** @Note : girilen id'e ait realty'i siler */
	@PostMapping(value = "/{realtyId}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer realtyId) {
		realtyService.deleteRealty(realtyId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/** @Note: girilen il/illere ait ilanları verir. */
	@GetMapping(value = "/{province}")
	public List<Realty> findByProvince(@PathVariable List<String> province) {
		return realtyService.getAllRealtyByProvince(province);
	}

	/** @Note: girilen duruma göre (active/passive) ilanları verir */
	@GetMapping(value = "/status/{status}")
	public List<Realty> findByStatus(@PathVariable String status) {
		return realtyService.getByStatus(status.toUpperCase());
	}
	
	/** @Note: girilen realtyId'e sahip realty'nin süresi istenilen ay kadar uzatılır.
	 * eger kullanıcı online ve istenilen sayı kadar paket'e sahipse */
	@PostMapping(value = "/{realtyId}/{extendMonth}")
	public ResponseEntity<HttpStatus> extendRealtyDate(@PathVariable Integer realtyId, Integer extendMonth) {
		return new ResponseEntity<>(
				realtyService.extendDate(realtyId, extendMonth) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
}
