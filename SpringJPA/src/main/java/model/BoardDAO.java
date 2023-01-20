package model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import vo.BoardVO;

//** DAO (Data Access Object)
// => CRUD 구현 
// C: create -> insert
// R: read   -> selectList, selectOne
// U: update -> update
// D: delete -> delete

@Repository
public class BoardDAO {

	@PersistenceContext
	// => EntityManager 객체 주입시 사용하는 애너테이션
	// => @Autowired 와 같은 역할,
	// 그러므로 EntityManager 인터페이스을 구현한 클래스가 생성되어 있어야 주입가능
	// 이 구현 클래스 생성은 root~~.xml 또는 servlet~~.xml 에서 한다.
	EntityManager entityManager;

	// ** selectList
	public List<BoardVO> selectList() {
		return entityManager.createQuery("from BoardVO b order by b.root desc, b.step asc").getResultList();
	} // selectList

	// ** selectOne
	public BoardVO selectOne(BoardVO vo) {
		return entityManager.find(BoardVO.class, vo.getSeq());
	} // selectOne
	
	/*   
	    ** Hibernate 의 C, U, D 처리 메서드
	      => persist() 또는 saveOrUpdate(), merge(), remove()
	      => 반드시 트랜잭션으로 처리 되어져야 함
	      => entitymanager 를 사용하는 경우 version 간의 충돌로 오류발생 확률 높음
	   
	   // ** Insert
	   public void insert(BoardVO vo) {
	      entityManager.persist(vo);
	   } //insert
	     
	   // ** Update
	   public void update(BoardVO vo) {
	      entityManager.merge(vo);
	   } //update
	     
	   // ** Delete
	   public void delete(BoardVO vo) {
	      entityManager.remove(vo);
	   } //delete
	*/   
	

} // class
