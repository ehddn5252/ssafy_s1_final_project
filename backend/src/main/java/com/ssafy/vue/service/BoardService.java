package com.ssafy.vue.service;

import java.util.List;
import java.util.Map;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.QnaDto;

public interface BoardService {
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public boolean writeBoard(Board board);
	public boolean updateBoard(Board board);
	public boolean deleteBoard(int articleno);
	//조회수 수정
	public boolean updateHit(int articleno);
	int likecheck(Map<String, Object> map);
	public int likeBoard(Map<String, Object> map);
	public int unlikeBoard(Map<String, Object> map);
	public List<Board> popularBoard();
	public List<Board> selectBoardByName(Map<String, String> map);
	public PageNavigation makePageNavigation(Map<String, String> map);
}
