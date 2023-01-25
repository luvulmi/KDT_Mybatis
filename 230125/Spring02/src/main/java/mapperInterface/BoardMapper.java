package mapperInterface;

import java.util.List;

import vo.BoardVO;

public interface BoardMapper {
	
	// ** selectList
	List<BoardVO> selectList();

	// ** selectOne
	BoardVO selectOne(BoardVO vo);

	// ** Insert
	int insert(BoardVO vo);

	// ** Update
	int update(BoardVO vo);

	// ** Delete
	int delete(BoardVO vo);

	// ** 조회수 증가
	int countUp(BoardVO vo);

	// ** Reply_Insert
	int rinsert(BoardVO vo);
	int stepUpdate(BoardVO vo);

} //interface
