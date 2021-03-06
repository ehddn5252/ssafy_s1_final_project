package com.ssafy.vue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.HouseDealDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.service.HouseMapService;

@CrossOrigin
@RestController
public class HappyHouseRestController {

	@Autowired
	HouseMapService service;



	@GetMapping(value = "/map/gugun/{sido}")
	public List<SidoGugunCodeDto> getGugun(@PathVariable String sido) {
		try {
			return service.getGugunInSido(sido);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping(value = "/map/dong/{gugun}")
	public List<HouseInfoDto> getDong(@PathVariable String gugun) {
		try {
			return service.getDongInGugun(gugun);
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping(value = "/map/housedeal/{dong}")
	public List<HouseDealDto> getHouseDeal(@PathVariable String dong) {
		try {
			return service.getDealsInDong(dong);
		} catch (Exception e) {
			return null;
		}
	}


	@GetMapping(value = "/map/range")
	public List<HouseDealDto> getHouseDealInRange(@RequestParam(value = "swlat") String swlat,
			@RequestParam(value = "swlng") String swlng, @RequestParam(value = "nelat") String nelat,
			@RequestParam(value = "nelng") String nelng, @RequestParam(value = "deal") String deal,
			@RequestParam(value = "infra") String infra) {
		try {
			if("20".equals(deal)) return service.getHouseDealInRange(swlat, swlng, nelat, nelng);
			String price = Math.round(Double.parseDouble(deal)*10000)+"";
//			System.out.println(price);
			List<HouseDealDto> ret = service.getHouseDealInRangeByPrice(swlat, swlng, nelat, nelng, price);
//			System.out.println(ret.size());
//			System.out.println(ret);
			return ret;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@GetMapping(value = "/map/search/latlng")
	public List<HouseDealDto> getSearchByLatLng(@RequestParam(value = "lat") String lat,
			@RequestParam(value = "lng") String lng) {
		try {
			return service.getSearchByLatLng(lat, lng);
		} catch (Exception e) {
			return null;
		}
	}

}
