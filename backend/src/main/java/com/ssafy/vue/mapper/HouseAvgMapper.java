package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.HouseAvgDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.dto.SidoGugunCodeDto;

@Mapper
public interface HouseAvgMapper {
	List<HouseAvgDto> getSidoAvg() throws SQLException;
	List<HouseAvgDto> getGugunAvg() throws SQLException;
	List<HouseAvgDto> getDongAvg() throws SQLException;

}