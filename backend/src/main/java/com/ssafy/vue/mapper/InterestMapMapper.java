package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.RegionDto;

@Mapper
public interface InterestMapMapper {
	
	// 공지 등록
	int insertInterest(RegionDto interestDto) throws Exception;

	// 공지삭제
	int deleteInterest(int InterestId) throws Exception;
	
	// 리스트
	public List<RegionDto> searchByUserID(String userId) throws Exception;

	public List<RegionDto> popularList() throws Exception;
	
	public List<RegionDto> selectByIdAndDongCode(String userId, String dongCode) throws Exception;

}
