package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.SidoGugunCodeDto;
//import com.ssafy.vue.service.HouseMapServiceImpl.TmpDto;
import com.ssafy.util.Calculate;
import com.ssafy.vue.dto.HouseAvgDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.mapper.HouseAvgMapper;
import com.ssafy.vue.mapper.HouseMapMapper;

@Service
public class HouseAvgServiceImpl implements HouseAvgService {
	
	@Autowired
	private HouseAvgMapper houseAvgMapper;

	@Override
	public List<HouseAvgDto> getSidoAvg() throws SQLException {
		// TODO Auto-generated method stub
		return houseAvgMapper.getSidoAvg();
	}

	@Override
	public List<HouseAvgDto> getGugunAvg() throws SQLException {
		// TODO Auto-generated method stub
		return houseAvgMapper.getGugunAvg();
	}

	@Override
	public List<HouseAvgDto> getDongAvg() throws SQLException {
		// TODO Auto-generated method stub
		return houseAvgMapper.getDongAvg();
	}

	
}
