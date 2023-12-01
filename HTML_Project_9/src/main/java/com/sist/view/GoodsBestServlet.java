package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;


@WebServlet("/GoodsBestServlet")
public class GoodsBestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//	사용자가 요청한 값을 받는다 (사용자가 보낸건 모두 request안에 있ㅇㅁ)
		String page=request.getParameter("page"); // JSP에서는 page를 this라 씀 (page가 이미 존재하는 객체여서)
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// DAO 연동
		GoodsDAO dao=GoodsDAO.newInstance();
		List<GoodsVO> list=dao.goodsListData(curpage, 3);
		int totalpage=dao.goodsTotalPage(3);
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// totalpage=33 => 
		if(endpage>totalpage)
			endpage=totalpage;
		/*
		 * 	1  2 ...... 10
		 *  |            |
		 *  startPage    EndPage
		 *  curpage => 1~10 => startpage=1
		 */
		out.write("<html>");
		out.write("<body>");
		out.write("<div class=row>");
		out.write("<h1 class=text-center>베스트 상품</h1>");
		for(GoodsVO vo:list)
		{
			String s=vo.getName();
			if(s.length()>30)
			{
				s=s.substring(0,30)+"...";
				vo.setName(s);
			}
			else
			{
				vo.setName(s);
			}
		}
		for(GoodsVO vo:list)
		{
			out.write("<div class=\"col-md-3\">");
			out.write("<div class=\"thumbnail\">");
			out.write("<a href=#>"); // 클릭할 때 상세보기 넘어가는 (링크 걸리는 위치)
			out.write("<img src="+vo.getPoster()+" alt=\"Lights\" style=\"width:100%\">");
			out.write("<div class=\"caption\">");
			out.write("<p>"+vo.getName()+"</p>");
			out.write("</div>");
			out.write("</a>");
			out.write("</div>");
			out.write("</div>");
		}
		out.write("</div>");
		out.write("<div class=row>"); // 페이지 출력
		out.write("<div class=text-center>");
		out.write("<ul class=\"pagination\">");
		if(startpage>1) // startPage => 1 11 21 31 ...
		{	
			out.write("<li><a href=MainListServlet?page="+(startpage-1)+"&mode=3>&lt;</a></li>");
			// 11page => 이전 => 10
		}
		 for(int i=startpage;i<=endpage;i++)
	       {
	            out.write("<li "+(i==curpage?"class=active":"")+"><a href=MainServlet?page="+i+"&mode=3>"+i+"</a></li>");
	            // 11page ==> 이전 ==> 10
	       }

		if(endpage<totalpage)// 10 20 30
		{
			out.write("<li><a href=MainListServlet?page="+(endpage+1)+"&mode=3>&gt;</a></li>");
		}
		
		out.write("</ul>");
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
	}

	
}
