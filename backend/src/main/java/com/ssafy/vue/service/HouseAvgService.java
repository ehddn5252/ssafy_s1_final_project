package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.HouseAvgDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

public interface HouseAvgService {

	List<HouseAvgDto> getSidoAvg() throws SQLException;
	List<HouseAvgDto> getGugunAvg() throws SQLException;
	List<HouseAvgDto> getDongAvg() throws SQLException;

}
