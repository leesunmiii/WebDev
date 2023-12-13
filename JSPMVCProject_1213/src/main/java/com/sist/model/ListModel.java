package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class ListModel implements Model {

   @Override
   public String handleRequest(HttpServletRequest request) {
	// type, page
			String type=request.getParameter("type");
			if(type==null)
				type="1";
			String page=request.getParameter("page");
			if(page==null)
				page="1";
			
			// 페이지 지정
			int curpage=Integer.parseInt(page);
			// 페이지에 해당되는 목록
			GoodsDAO dao=new GoodsDAO();
			List<GoodsVO> list=dao.goodsListData(curpage);
			int totalPage=dao.goodsTotalPage();
			
			final int BLOCK=10;
			int startpage=((curpage-1)/BLOCK*BLOCK)+1;
			int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endpage>totalPage)
				endpage=totalPage;
			
			//	JSP에서 출력할 데이터를 전송
			request.setAttribute("list", list);
			request.setAttribute("curpage", curpage);
			request.setAttribute("startPage", startpage);
			request.setAttribute("endPage", endpage);
      return "board/list.jsp";
   }

}















