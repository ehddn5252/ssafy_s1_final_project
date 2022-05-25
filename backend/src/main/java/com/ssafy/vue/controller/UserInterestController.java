package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.service.InterestMapService;

import io.swagger.annotations.ApiOperation;

import com.ssafy.vue.dto.RegionDto;


// CrossOrigin("localhost:8080") 은 해당 사용자만 사용할 수 있게 한다.
@CrossOrigin("*")
@RestController
@RequestMapping("/interest")
public class UserInterestController {
	
	private final Logger logger = LoggerFactory.getLogger(UserInterestController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	

	
	@Autowired
	private InterestMapService interestService;
	
//	@GetMapping("/list")
//	public String interest(){
//		return "interest";
//	}
	
	@GetMapping("/popular/list")
	public ResponseEntity<List<RegionDto>> popularList() throws Exception {
		System.out.println("popularList  들어와짐");

		return new ResponseEntity<List<RegionDto>>(interestService.popularList(),HttpStatus.OK);
	}
		
	
	@GetMapping("/list")
	public ResponseEntity<List<RegionDto>> interestList(@RequestParam("userId") String userId) throws Exception {
		System.out.println("select list 들어와짐");

		return new ResponseEntity<List<RegionDto>>(interestService.searchByUserID(userId),HttpStatus.OK);
	}
	
	@DeleteMapping("/list")
	public ResponseEntity<String> interestDelete(@RequestParam("interestId") int interestId) throws Exception {
		System.out.println("delete list 들어와짐");
		if (interestService.deleteInterest(interestId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	
	@PostMapping("/insert")
	public ResponseEntity<String> insertInterest(@RequestBody RegionDto r) throws Exception{
		System.out.println("in UserInterestController");
		System.out.println(r.getUserId());
		System.out.println(r.getSigugunCode());
		System.out.println(r.getDongCode());
		System.out.println(r.getAreaName());
		System.out.println("==============");
		logger.debug(r.getUserId()+" "+r.getSidoCode()+" "+r.getSigugunCode()+" "+r.getDongCode());
		if(interestService.insertInterest(r)){
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
//    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PostMapping
//	public ResponseEntity<String> writeBoard(@RequestBody RegionDto r) throws Exception {
//		logger.debug("writeBoard - 호출");
//		if (interestService.insertInterest(r)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}


}
