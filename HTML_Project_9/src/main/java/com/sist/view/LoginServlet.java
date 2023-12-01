package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import com.sist.dao.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.write("<style>");
		out.write("a.container{margin-top:50px}");
		out.write("a.row{margin:0px auto;width:300px}");
		out.write("h3{text-align:center}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<div class=container>");
		out.write("<div class=row>");
		out.write("<form method=post action=LoginServlet>");
		out.write("<table class=table>");
		out.write("<tr>");
		out.write("<th width=20% class=\"text-center danger\">ID</th>");
		out.write("<td width=80%>");
		out.write("<input type=text name=id size=15 required>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=20% class=\"text-center danger\">PWD</th>");
		out.write("<td width=80%>");
		out.write("<input type=password name=pwd size=15 required>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td colspan=2 class=text-center>");
		out.write("<input type=submit value=로그인 class=\"btn btn-sm btn-primary\">");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("</table>");
		out.write("</div>");
		out.write("</div>");
		out.write("</body>");
		out.write("</head>");
		out.write("</html>");
		
	}

	//										-------- 정보				  -------- 화면 이동시 정보 저장
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doPost => 처리용(로그인 처리, 회원처리...)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		// 1. 전송값을 받는다
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
	
		//	2. DAO 연동
		MemberDAO dao=MemberDAO.newInstance();
		MemberVO vo=dao.memberLogin(id, pwd);
		if(vo.getMsg().equals("OK"))
		{
			// 화면 이동
			HttpSession session=request.getSession();
			// 사용자가 가지고 있다 (브라우저마다 한개씩 생성)
			/*
			 * 		session : 서버에 저장
			 * 				  => 저장시에 object로 저장
			 * 				  ** 쿠키의 단점은 문자열만 저장할 수 있다는 것
			 * 				  ** 장바구니 => session
			 */
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			//								======= "홍길동"님 로그인중입니다
			// Map방식 => 키가 중복되면 안됨
			// 회원수정 => 이름변경 => session에 다시 저장
			//session.setMaxInactiveInterval(0);
			//session의 저장 기간의 디폴트 => 1800초 => 30분
			/*
			 * 		1.	setAttribute() => 세션에 저장
			 * 		2. 	getAttribute() => 세션에 저장된 값 읽기
			 * 		3. 	removeAttribute(키) => 키만 삭제
			 * 		4. invalidate() => 전체 삭제 => 로그아웃
			 * 		5. isNew() => 처음
			 * 		6. setMaxinactiveInterval() : 기간 설정
			 * 			=> 기본 디폴트가 30분 (초단위 : 1800)
			 * 		7. getId() : session 생성시마다 자동 생성
			 * 			=> 클라이언트마다 1개만 가지고 있다
			 * 			=> 채팅
			 * 		=================================== session의 주요 메소드
			 */
			
			response.sendRedirect("MainServlet"); 
		}
		else if(vo.getMsg().equals("NOID"))
		{
			out.write("<script>");
			out.write("<alert(\"아이디가 존재하지 않습니다\");"); /*alert : 아이디가 존재하지않습니다 창 뜨게 하는거*/
			out.write("<history.back()>");
			out.write("</script>");
			
		}
		else if(vo.getMsg().equals("NOPWD"))
		{
			out.write("<script>");
			out.write("<alert(\"비밀번호가 존재하지 않습니다\");"); /*alert : 아이디가 존재하지않습니다 창 뜨게 하는거*/
			out.write("<history.back()>");
			out.write("</script>");
		}
		
		
	}

}
