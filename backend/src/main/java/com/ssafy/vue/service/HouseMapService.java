package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.dto.HouseInfoDto;

import com.ssafy.util.PageNavigation;

import com.ssafy.vue.dto.BaseAddressDto;

import com.ssafy.vue.dto.HouseDealDto;

public interface HouseMapService {


   List<SidoGugunCodeDto> getSido() throws Exception;
   List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
   List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
   List<HouseInfoDto> getAptInDong(String dong) throws Exception;

   List<HouseInfoDto> getAptInDong2(String dongCode) throws Exception;

    List<HouseInfoDto> getAptInName(String aptName, String dong) throws Exception;
//   List<HouseInfoDto> getRecommendList(String dong, String price, String houseSize) throws Exception;

//-------- 평균값을 위한 부분 ------------
     List<HouseInfoDto> getDongList(String gugun) throws SQLException;
     List<HouseInfoDto> getLngLat(String dongName) throws SQLException;

// ------------------------------

   // 05.23 모달house deal 추가
     List<HouseDealDto> getHouseDealInApt(int dealCount, String aptCode);
     
     // 페이징을 위한 추가
	List<HouseInfoDto> selectAptByName(Map<String, String> map);
	PageNavigation makePageNavigation(Map<String, String> map);
  
  	  List<BaseAddressDto> getSidoLatLon(String sido);
	  List<BaseAddressDto> getGugunLatLon(String gugun);
	  
	  // 복붙 추가
	  List<HouseDealDto> getDealsInDong(String dong) throws Exception;
	  List<HouseDealDto> getHouseDealInRange(String swlat, String swlng, String nelat, String nelng) throws Exception;
		List<HouseDealDto> getSearchByLatLng(String lat, String lng) throws Exception;
		List<HouseDealDto> getHouseDealInRangeByPrice(String swlat, String swlng, String nelat, String nelng, String price) throws Exception;
}

