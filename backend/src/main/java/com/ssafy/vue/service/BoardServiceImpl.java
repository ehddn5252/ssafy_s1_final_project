package com.ssafy.vue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.QnaDto;
import com.ssafy.vue.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<Board> retrieveBoard() {
		return boardMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		return boardMapper.selectBoardByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}

	@Override
	public boolean updateHit(int articleno) {
		// TODO Auto-generated method stub
		return boardMapper.updateHit(articleno)==1;
	}

	@Override
	public int likecheck(Map<String, Object> map) {
		return boardMapper.likecheck(map);
	}

	@Override
	public int likeBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.likeBoard(map);
	}

	@Override
	public int unlikeBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.unlikeBoard(map);
	}

	@Override
	public List<Board> popularBoard() {
		// TODO Auto-generated method stub
		return boardMapper.popularBoard();
	}

	@Override
	public List<Board> selectBoardByName(Map<String, String> map) {
	      Map<String, Object> param = new HashMap<String, Object>();
	      String key = map.get("key");
	      if("userid".equals(key))
	         key = "q.userid";
	      param.put("key", key == null ? "" : key);
	      param.put("word", map.get("word") == null ? "" : map.get("word"));
	      int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
	      int sizePerPage = Integer.parseInt(map.get("spp"));
	      int start = (currentPage - 1) * sizePerPage;
	      param.put("start", start);
	      param.put("spp", sizePerPage);
	      return boardMapper.selectBoardByName(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) {
		PageNavigation pageNavigation = new PageNavigation();
	      
	      int naviSize = 10;
	      int currentPage = Integer.parseInt(map.get("pg"));
	      int sizePerPage = Integer.parseInt(map.get("spp"));
	      
	      pageNavigation.setCurrentPage(currentPage);
	      pageNavigation.setNaviSize(naviSize);
	      int totalCount = boardMapper.getTotalCount(map);
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

}