package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.RegionDto;

public interface InterestMapService {
	
	// 관심지역 등록
	boolean insertInterest(RegionDto interestDto) throws Exception;

	// 관심지역 삭제
	boolean deleteInterest(int interestId) throws Exception;
	
	List<RegionDto> searchByUserID(String userId) throws Exception;
	
}
