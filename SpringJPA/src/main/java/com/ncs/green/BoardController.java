package com.ncs.green;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import service.BoardService;
import vo.BoardVO;

@Controller
public class BoardController {
	@Autowired
	BoardService service;

	   // ** BoardList: /blist
	   @RequestMapping(value = "/blist", method=RequestMethod.GET )
	   public ModelAndView blist(ModelAndView mv) {
	      mv.addObject("banana", service.selectList());      
	      mv.setViewName("/board/boardList");
	      return mv;
	   }//blist
	   
	   // ** BoardDetail: /bdetail
	   @RequestMapping(value = "/bdetail", method=RequestMethod.GET)
	   public ModelAndView bdetail(HttpServletRequest request, ModelAndView mv, BoardVO vo) {
	       
	      vo=service.selectOne(vo);
	      mv.addObject("apple", vo);  
	      // ** View 처리
	      // => Update 요청이면 bupdateForm.jsp
	      String uri = "/board/boardDetail";
	      mv.setViewName(uri);
	      return mv;
	   }//bdetail   

} // class
