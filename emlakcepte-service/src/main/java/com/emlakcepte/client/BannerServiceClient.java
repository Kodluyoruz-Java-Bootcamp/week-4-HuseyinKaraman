package com.emlakcepte.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emlakcepte.client.model.Banner;

@FeignClient(value = "emlakcepte-banner", url = "http://localhost:9090")
public interface BannerServiceClient  {


	/** emlakcepte-banner-service'deki getAll'i kullanacak */
	@GetMapping(value = "/banners")
	public ResponseEntity<List<Banner>> getAll();

	/** emlakcepte-banner-service'deki create'i kullanacak */
	@PostMapping(value = "/banners")
	public ResponseEntity<Banner> create(@RequestBody Banner banner);

}
