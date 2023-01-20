package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BoardDAO;
import vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
   @Autowired
   BoardDAO dao; 
   
   // ** selectList
   @Override
   public List<BoardVO> selectList() {
      return dao.selectList();
   }
   // ** selectOne
   @Override
   public BoardVO selectOne(BoardVO vo) {
      return dao.selectOne(vo);
   }
   
} //class
