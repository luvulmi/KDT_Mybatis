package service;

import java.util.List;

import vo.BoardVO;

public interface BoardService {

	   // ** selectList
	   List<BoardVO> selectList();

	   // ** selectOne
	   BoardVO selectOne(BoardVO vo);

	} //class