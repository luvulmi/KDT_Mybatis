package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapperInterface.BoardMapper;
import vo.BoardVO;

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	//=> 아래 생성문의 "=" 의 역할 (반드시 생성해야함)
	//BoardDAO dao; 
	//BoardDAO dao = new BoardDAO(); // -> @Repository
	
	// => BoardMapper interface 로 교체,
	//    이 Mapper 를 통해서 BoardMapper.xml 의 SQL 구문 접근
	BoardMapper mapper;
	
	// ** selectList
	@Override
	public List<BoardVO> selectList() {
		return mapper.selectList();
	}
	// ** selectOne
	@Override
	public BoardVO selectOne(BoardVO vo) {
		return mapper.selectOne(vo);
	}
	// ** Insert
	@Override
	public int insert(BoardVO vo) {
		return mapper.insert(vo);
	}
	// ** Update
	@Override
	public int update(BoardVO vo) {
		return mapper.update(vo);
	}
	// ** Delete
	@Override
	public int delete(BoardVO vo) {
		return mapper.delete(vo);
	}
	// ** 조회수 증가
	@Override
	public int countUp(BoardVO vo) {
		return mapper.countUp(vo);
	}
	// ** Reply_Insert
	@Override
	public int rinsert(BoardVO vo) {
		if ( mapper.rinsert(vo)>0 ) {
			// stepUpdate
			System.out.println("** stepUpdate Count => "+mapper.stepUpdate(vo));
			return 1 ;
		}else return 0;
	}

} //class
