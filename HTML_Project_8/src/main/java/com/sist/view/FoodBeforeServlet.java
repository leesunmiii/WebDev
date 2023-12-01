package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FoodBeforeServlet")
public class FoodBeforeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fno=request.getParameter("fno");
		Cookie cookie=new Cookie("food_"+fno,fno);
		//							키		  값 => map
		
		// 저장 위치
		cookie.setPath("/");
		
		// 저장기간 얼마후에 사라질건지
		cookie.setMaxAge(60*60*24); //24시간 저장한다는 뜻
		//				--------초단위
		
		//클라이언트로 전송 (브라우저로 전송)
		response.addCookie(cookie);
		
		// 상세보기로 이동해라
		response.sendRedirect("FoodDetailServlet?fno="+fno);
	}

}
