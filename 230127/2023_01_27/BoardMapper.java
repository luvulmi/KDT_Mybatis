package mapperInterface;

import java.util.List;

import criTest.Criteria;
import criTest.SearchCriteria;
import vo.BoardVO;

public interface BoardMapper {
	
	// ** Board Check List
	List<BoardVO> checkList(SearchCriteria cri);
	int checkCount(SearchCriteria cri);
	
	// ** SearchCriteria PageList
	List<BoardVO> searchList(SearchCriteria cri);
	int searchTotalCount(SearchCriteria cri);
	
	// ** Criteria PageList
	List<BoardVO> criList(Criteria cri);
	int criTotalCount();
	
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
