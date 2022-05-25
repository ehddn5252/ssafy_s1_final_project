package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.SidoGugunCodeDto;
//import com.ssafy.vue.service.HouseMapServiceImpl.TmpDto;
import com.ssafy.util.Calculate;

import com.ssafy.util.PageNavigation;

import com.ssafy.vue.dto.BaseAddressDto;

import com.ssafy.vue.dto.HouseDealDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
   
   @Autowired
   private HouseMapMapper houseMapMapper;


   
   @Override
   public List<SidoGugunCodeDto> getSido() throws Exception {
      return houseMapMapper.getSido();
   }

   @Override
   public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
      return houseMapMapper.getGugunInSido(sido);
   }
  
  
  	
  	@Override
  	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
  		return houseMapMapper.getDongInGugun(gugun);
  	}
  	
  	@Override
  	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
  		return houseMapMapper.getAptInDong(dong);
  	}
  
  	@Override
	public List<BaseAddressDto> getSidoLatLon(String sido) {
		
		return houseMapMapper.getSidoLatLon(sido);
	}



   @Override
   public List<HouseInfoDto> getAptInDong2(String dongCode) throws Exception {
      return houseMapMapper.getAptInDong2(dongCode);
   }

   
   //========================2022.05.23 추가



   //======================================= 2022.05.18 11:52 추가된 부분
   
   @Override
   public List<HouseInfoDto> getAptInName(String aptName, String dong) throws Exception {
      return houseMapMapper.getAptInName(aptName, dong);
   }
   
   
   @Override
   public List<HouseInfoDto> getDongList(String gugun) throws SQLException {
      
      return houseMapMapper.getDongList(gugun);
   }

  
     @Override
   public List<HouseInfoDto> getLngLat(String dongName) throws SQLException {
      // TODO Auto-generated method stub
      return houseMapMapper.getLngLat(dongName);
   }
     
	 @Override
	 public List<HouseDealDto> getHouseDealInApt(int dealCount, String aptCode) {
	    // TODO Auto-generated method stub
	    return houseMapMapper.getHouseDealInApt(dealCount,aptCode);
	 }


	@Override
	public List<HouseInfoDto> selectAptByName(Map<String, String> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("aptName", map.get("aptName") == null ? "" : map.get("aptName"));
	      param.put("dong", map.get("dong"));
	      int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
	      int sizePerPage = Integer.parseInt(map.get("spp"));
	      int start = (currentPage - 1) * sizePerPage;
	      param.put("start", start);
	      param.put("spp", sizePerPage);
	      return houseMapMapper.selectAptByName(param);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) {
		PageNavigation pageNavigation = new PageNavigation();
	      
	      int naviSize = 10;
	      int currentPage = Integer.parseInt(map.get("pg"));
	      int sizePerPage = Integer.parseInt(map.get("spp"));
	      
	      pageNavigation.setCurrentPage(currentPage);
	      pageNavigation.setNaviSize(naviSize);
	      int totalCount = houseMapMapper.getTotalCount(map);
	      pageNavigation.setTotalCount(totalCount);
	      int totalPageCount = (totalCount - 1) / sizePerPage + 1;
	      pageNavigation.setTotalPageCount(totalPageCount);
	      boolean startRange = currentPage <= naviSize;
	      pageNavigation.setStartRange(startRange);
	      boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
	      pageNavigation.setEndRange(endRange);
	      pageNavigation.makeNavigator();
	      
	      return pageNavigation;
	}
	

	@Override
	public List<BaseAddressDto> getGugunLatLon(String gugun) {
		return houseMapMapper.getGugunLatLon(gugun);
	}
	
	@Override
	public List<HouseDealDto> getDealsInDong(String dong) throws Exception {
		return houseMapMapper.getDealsInDong(dong);
	}


	
	
	@Override
	public List<HouseDealDto> getHouseDealInRange(String swlat, String swlng, String nelat, String nelng)
			throws Exception {
		return houseMapMapper.getHouseDealInRange(swlat, swlng, nelat, nelng);
	}

	@Override
	public List<HouseDealDto> getSearchByLatLng(String lat, String lng) throws Exception {
		return houseMapMapper.getSearchByLatLng(lat, lng);
	}

	@Override
	public List<HouseDealDto> getHouseDealInRangeByPrice(String swlat, String swlng, String nelat, String nelng,
			String price) throws Exception {
//		System.out.println("service:"+price);
		return houseMapMapper.getHouseDealInRangeByPrice(swlat, swlng, nelat, nelng, price);
	}


	

	
}

