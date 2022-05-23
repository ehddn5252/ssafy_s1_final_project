package com.ssafy.vue.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.SidoGugunCodeDto;
import com.ssafy.vue.dto.HouseAvgDto;
import com.ssafy.vue.dto.HouseInfoDto;
import com.ssafy.vue.service.HouseAvgService;
import com.ssafy.vue.service.HouseMapService;

@RestController
@RequestMapping("/avg")
@CrossOrigin("*")
public class HouseAvgController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseAvgController.class);

	@Autowired
	private HouseAvgService houseAvgService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<HouseAvgDto>> sido() throws Exception {
		logger.debug("sido : {}", houseAvgService.getSidoAvg());
		return new ResponseEntity<List<HouseAvgDto>>(houseAvgService.getSidoAvg(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<HouseAvgDto>> gugun() throws Exception {
		logger.debug("gugun : {}", houseAvgService.getGugunAvg());
		return new ResponseEntity<List<HouseAvgDto>>(houseAvgService.getGugunAvg(), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseAvgDto>> dong() throws Exception {
		logger.debug("gugun : {}", houseAvgService.getDongAvg());
		return new ResponseEntity<List<HouseAvgDto>>(houseAvgService.getDongAvg(), HttpStatus.OK);
	}

}
