package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.mapper.InterestMapMapper;
import com.ssafy.vue.dto.RegionDto;

@Service
public class InterestMapServiceImpl implements InterestMapService {
	
	@Autowired
	private InterestMapMapper interestMapMapper;
	
	// 관심지역 등록
	@Override
	public boolean insertInterest(RegionDto interestDto) throws Exception{
		System.out.println("interestDto");
		System.out.println(interestDto.toString());
		System.out.println("========");
		return interestMapMapper.insertInterest(interestDto)==1;
	}

	@Override
	public boolean deleteInterest(int interestId) throws Exception {
		return interestMapMapper.deleteInterest(interestId)==1;
	}

	@Override
	public List<RegionDto> searchByUserID(String userId) throws Exception {
		return interestMapMapper.searchByUserID(userId);
	}

	@Override
	public List<RegionDto> popularList() throws Exception {
		
		return interestMapMapper.popularList();
	}

	
}
