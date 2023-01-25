package springMybatis;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mapperInterface.MemberMapper;
import vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_InterfaceMapper {
	
	@Autowired  // 성공: MemberMapper mapper = new MemberMapper구현객체 ;
	MemberMapper mapper ;
	// => 위의 interface를 구현클래스를 자동주입 받아야함
	//    그러나 이 interface 구현클래스를 직접 만들지 않았고, ~Mapper.xml 만 작성했고
	//    ~ServiceImpl 에서는 ~Mapper.xml 과 바로 연동시켜놓음(메서드명으로)  
	//    그러면 스프링이 실행과정에서 이 클래스를 만들어 실행해줌
	//    이것을 위한 경로 맞춰주는 설정이
	//    <mybatis-spring:scan base-package="mapperInterface"/> 
	
	@Autowired
	MemberVO vo;
	
	// ** Mapper의 동작 Test

	public void mapperTest() {
		assertNotNull(mapper);
		System.out.println("** Mapper Interface => "+mapper.getClass().getName());
		// => getClass().getName() : 실제동작하는 클래스(MemberMapper구현객체)  의 이름 확인가능
		//    이를 통해 우리는 Mapper interface 만 작성했지만, 
		//    내부적으로는 동일한 타입의 클래스가 만들어졌음을 알 수 있다.  
	} //mapperTest
	
	// ** Mapper method Test
	// 1) selectOne
	@Test
	public void selectOneTest() {
		// ** 매개변수 id & @Select 적용 연습 추가 Test
		System.out.println("** selectOneID => "+mapper.selectOneID("apple"));
		
		// 존재하는 id , 존재하지않는 id 비교
		vo.setId("banana"); // banana / "black
		
		vo=mapper.selectOne(vo);
		//mapper.selectOne(vo); 
		// => Mybatis 적용하는 경우 SQL구문처리에서 매개변수의 주소를 공유하지않기 때문에
		//    selectOne 처리 결과를 전달받아야함 
		System.out.println("** vo => "+vo);
		assertNotNull(vo);
	} //selectOne
	
	// 2) insert & delete
	public void insertTest() {
		vo.setId("mapper");
		vo.setPassword("12345!");
		vo.setName("스프링");
		vo.setAge(20);
		vo.setInfo("성공이냐 실패냐 ~~~");
		vo.setJno(7);
		vo.setPoint(99.88);
		vo.setBirthday("1999-09-09");
		// => 성공:1 , 실패:0
		assertEquals(mapper.insert(vo), 1);    
	} //insert

	public void deleteTest() {
		vo.setId("mapper");
		assertEquals(mapper.delete(vo), 1);    
	}

	// 3) @Param Test (매개변수 id, jno)
	// => Mybatis 에서 2개이상의 매개변수 처리
	public void paramTest() {
		// 존재하는 Data , 존재하지않는 Data 비교
		vo=mapper.selectJoOne("banana",9);
		System.out.println("** vo => "+vo);
		assertNotNull(vo);
	}
	
	// 4) totalCount
	// => admin을 제외한 전체 member count, 출력 확인
	// => interface에 메서드추가, mapper에 sql 추가, Test메서드 작성
	public void totalCountTest() {
		System.out.println("** Member_Record Count => "+mapper.totalCount());
	}
	
	// 5) mapper (xml) 없이 Test 하기
	// => MemberMapper.java 에 selectList2 추가 후 Test
	// => Mapper interface 의 메서드명 위에 @ 을 이용하여 sql 구문 작성
	//    @Select("select * from member where lev='D'")
	// => @select : mapper 없이 일반 쿼리구문을 작성해서 사용할 수 있도록 해줌
	public void selectList2Test() {
		System.out.println("** @Select Test => "+mapper.selectList2());
	}

} //class
