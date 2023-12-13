package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import com.sist.vo.*;
public class DetailModel implements Model {
// .do => 이 클래스 안에 handleRequest 메소드를 호출하라는 뜻
	@Override
	public String handleRequest(HttpServletRequest request) {
		//request.setAttribute("msg", "게시판 상세보기");
		
		String no=request.getParameter("no");
		
		// DAO
		GoodsDAO dao=GoodsDAO.newInstance();
		GoodsVO vo=dao.goodsDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		// request
		return "board/detail.jsp";
	}

}
