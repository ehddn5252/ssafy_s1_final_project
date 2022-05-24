package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.HouseDealDto;
import com.ssafy.vue.dto.BaseAddressDto;



@Mapper
public interface HouseMapMapper {


   List<SidoGugunCodeDto> getSido() throws SQLException;
   List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
   List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
   List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
   

   //2022.05.20 추가
   List<HouseInfoDto> getAptInDong2(String dongCode) throws SQLException;
   
   /////  ============2022.05.18 12:42 add
   List<HouseInfoDto> getAptInName(String aptName, String dong) throws SQLException;
   
   //   ------ 지도 평균값을 위한 함수------
   List<HouseInfoDto> getDongList(String gugun) throws SQLException;
   List<HouseInfoDto> getLngLat(String dongName) throws SQLException;
   
   // 2022.05.23 house deal 부분 리턴하기 위해서 추가
   List<HouseDealDto> getHouseDealInApt(int dealCount, String aptCode);
   
   // 페이징 기능을 위한 것
	List<HouseInfoDto> selectAptByName(Map<String, Object> param);
	int getTotalCount(Map<String, String> map);
  
  	List<BaseAddressDto> getSidoLatLon(String sido);
	List<BaseAddressDto> getGugunLatLon(String gugun);
}

