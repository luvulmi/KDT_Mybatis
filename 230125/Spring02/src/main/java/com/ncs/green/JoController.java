package com.ncs.green;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.JoService;
import service.MemberService;
import vo.JoVO;
import vo.MemberVO;

@Controller
public class JoController {
	
	@Autowired
	JoService service;
	@Autowired
	MemberService mservice;
	 
	// ** JoList
	@RequestMapping(value="/jlist")
	public ModelAndView jlist(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		
		List<JoVO> list = new ArrayList<JoVO>();
    	list = service.selectList();
    	if ( list!=null ) {
    		mv.addObject("banana", list);  // request.setAttribute(...) 와 동일효과
    	}else {
    		mv.addObject("message", "~~ 출력 자료가 없습니다 ~~");
    	}
    	mv.setViewName("/jo/joList");
    	return mv;
	}
	
	// ** JoDetail
	// => 아랫쪽에 조원목록 출력
	// => memjo Table에서 selectOne  ->  apple 
	// => member Table에서 조건검색 jno=#{jno}  ->  banana 
	@RequestMapping(value="/jdetail", method=RequestMethod.GET)
	public ModelAndView jdetail(HttpServletRequest request, HttpServletResponse response, 
					ModelAndView mv, JoVO vo, MemberVO mvo) {

		// ** 수정 성공후 redirect 요청으로 전달된 경우 message 처리
		if ( request.getParameter("message")!=null &&  request.getParameter("message").length() > 0 )
			mv.addObject("message", request.getParameter("message")) ;
		System.out.println("***** jno 전달확인 => "+vo.getJno());
		System.out.println("***** jname 한글 전달확인 => "+vo.getJname());
		
		// 1. 요청분석
		String uri = "/jo/joDetail";
		
		// 2. Service 처리
		vo = service.selectOne(vo);
		if ( vo != null ) {
			// 2.1) 수정요청 인지 확인
			if ( "U".equals(request.getParameter("jCode")))
				uri = "/jo/jupdateForm";
			else {
				// ** 조원목록 읽어오기
				mv.addObject("banana", mservice.selectListJo(mvo));
			}
			
			// 2.2)	결과전달		
			mv.addObject("apple", vo);
		}else mv.addObject("message", "~~ 조번호에 해당하는 자료가 없습니다. ~~");
		
		mv.setViewName(uri);
		return mv;
	} //jdetail
	
	// ** Insert 
	@RequestMapping(value="/jinsertf")
	public ModelAndView jinsertf(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		mv.setViewName("/jo/jinsertForm");
		return mv;
	}
	@RequestMapping(value="/jinsert", method=RequestMethod.POST)
	public ModelAndView jinsert(HttpServletRequest request, 
			HttpServletResponse response, ModelAndView mv, JoVO vo, RedirectAttributes rttr) {

		// 1. 요청분석
		// => insert 성공 : jlist (redirect 요청, message 전달)
		//    insert 실패 : jinsertForm.jsp  
		String uri = "redirect:jlist";
		// 2. Service 처리
		if ( service.insert(vo)>0 ) {
			rttr.addFlashAttribute("message", "~~ 새로운조 등록 성공 ~~");
			rttr.addFlashAttribute("mytest", "addFlashAttribute 메서드 Test");
		}else {
			mv.addObject("message", "~~ 새로운조 등록 실패, 다시 하세요 ~~");
			uri = "/jo/jinsertForm";
		}
		// 3. 결과(ModelAndView) 전달 
		mv.setViewName(uri);
		return mv;
	} //jinsert
	

	// ** Update : 글수정하기
	@RequestMapping(value="/jupdate", method=RequestMethod.POST)
	public ModelAndView jupdate(HttpServletRequest request, HttpServletResponse response, 
									ModelAndView mv, JoVO vo) {
		// 1. 요청분석
		// => Update 성공: joDetail.jsp
		//           실패: 재수정 유도 -> jupdateForm.jsp
		String uri = "redirect:jdetail";  
				
		mv.addObject("apple",vo);
		// => Update 성공/실패 모두 출력시 필요하므로
		
		// 2. Service 처리
		if ( service.update(vo) > 0 ) {
			//rttr.addFlashAttribute("message", "~~ 조 수정 성공 ~~"); 
			
			// ** Spring 의 redirect
			// => mv.addObject 에 보관한 값들을 url에 붙여 전달해줌 Test 
			// => 단, 이렇게 url에 붙여 전달하면서 RedirectAttributes rttr 사용시,
			//    jdetail 메서드의 매개변수에서 VO로 전달된 prameter를 받는 경우에는 500오류 발생함
			//    VO로 받지 않는 경우에는 퀴리스트링으로 전달하면서 RedirectAttributes rttr 사용가능함.
			mv.addObject("jno", vo.getJno());
			mv.addObject("jname", vo.getJname());
			mv.addObject("message", "~~ 조 수정 성공 ~~");
		}else {
			mv.addObject("message", "~~ 조 수정 실패, 다시 하세요 ~~");
			uri = "/jo/jupdateForm";
		}
		// 3. 결과(ModelAndView) 전달 
		mv.setViewName(uri);
		return mv;
	}
	
	// ** Delete : 글 삭제
	@RequestMapping(value="/jdelete")
	public ModelAndView jdelete(HttpServletRequest request, HttpServletResponse response, 
									ModelAndView mv, JoVO vo, RedirectAttributes rttr) {
		// 1. 요청분석
		// => Delete 성공: redirect:jlist
		//           실패: message 표시, redirect:jdetail
		String uri = "redirect:jlist";
		
		// 2. Service 처리
		if ( service.delete(vo) > 0 ) {
			rttr.addFlashAttribute("message", "~~ 조 삭제 성공 ~~"); 
		}else {
			rttr.addFlashAttribute("message", "~~ 조 삭제 실패, 다시 하세요 ~~");
			uri = "redirect:jdetail?jno="+vo.getJno();
		} // Service
		
		// 3. 결과(ModelAndView) 전달 
		mv.setViewName(uri);
		return mv;
	} //jdelete

} //class
