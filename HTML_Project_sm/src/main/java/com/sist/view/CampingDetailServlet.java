package com.sist.view;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import java.util.*;


@WebServlet("/CampingDetailServlet")
public class CampingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		// 사용자가 보내준 데이터 받기
		String Mno=request.getParameter("Mno");
		// fno에 해당하는 데이터 정보를 가지고 온다 => FoodDAO
		
		// 데이터베이스 연동
		CampingDAO dao=CampingDAO.newInstance();
		CampingVO vo=dao.CampingDetailData(Integer.parseInt(Mno));
		
		// 읽어온 데이터를 HTML에 출력
		out.write("<html>");
		out.write("<head>"); // CSS나 JavaScript가 있는 경우 <head>태그 사용 => 없는 경우에는 생략가능
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.write("<style type=text/css>");
		out.write(".container{margin-top:50px}");
		// margin:10px 	10px 	10px 	10px
		// 		  top	right	bottom	left
		out.write(".row{margin:0px auto;width:900px}"); // 모든 row는 가운데 정렬해라
		out.write("a{margin-left:5px}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>"); // 화면 UI가 나오는 부분
		out.write("<div class=container>");
		out.write("<div class=row>"); // 맛집 이미지
		out.write("<table class=table>");
		out.write("<tr>");
//		StringTokenizer st=new StringTokenizer(vo.getPoster(),"^");
//		while(st.hasMoreTokens())
		
			out.write("<td>");
			out.write("<img src="+vo.getPic1()+" style=\"width:100%\" class=img-rounded>");
			out.write("</td>");
			
			out.write("<td>");
			out.write("<img src="+vo.getPic2()+" style=\"width:100%\" class=img-rounded>");
			out.write("</td>");
			
			out.write("<td>");
			out.write("<img src="+vo.getPic3()+" style=\"width:100%\" class=img-rounded>");
			out.write("</td>");
		
		out.write("</tr>");
		out.write("</table>");
		out.write("</div>"); //row end
		out.write("<div style=\"height:30px\"></div>");
		out.write("<div class=row>");
		out.write("<div class=col-sm-8>"); //8+4=12
		out.write("<table class=table>");
		out.write("<tr>");
		out.write("<td colspan=2>");
		out.write("<h3>"+vo.getTitle()+"&nbsp;</h3>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=15%>주소</th>");
		out.write("<td width=85%>"+vo.getLoc()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=15%>전화</th>");
		out.write("<td width=85%>"+vo.getNum()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=15%>캠핑장 환경</th>");
		out.write("<td width=85%>"+vo.getEnvir()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=15%>캠핑장 유형</th>");
		out.write("<td width=85%>"+vo.getCategory()+"</td>");
		out.write("</tr>");

		out.write("<tr>");
		out.write("<th width=15%>운영기간</th>");
		out.write("<td width=85%>"+vo.getSeason()+"</td>");
		out.write("</tr>");

		out.write("<tr>");
		out.write("<th width=15%>운영일</th>");
		out.write("<td width=85%>"+vo.getOpenclose()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=15%>홈페이지</th>");
		out.write("<td width=85%>"+vo.getHomepage()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=15%>주변이용가능시설</th>");
		out.write("<td width=85%>"+vo.getFacility()+"</td>");
		out.write("</tr>");

//		if(!vo.getMenu().equals("no"))
//		{
//			out.write("<tr>");
//			out.write("<th width=15%>메뉴</th>");
//			out.write("<td width=85%>");
//			out.write("<ul>");
//			st=new StringTokenizer(vo.getMenu(),"원");
//			while(st.hasMoreTokens())
//			{
//				out.write("<li>"+st.nextToken()+"원</li>");
//			}
//			out.write("</ul>");
//			out.write("</td>");
//			out.write("</tr>");
//		}
		
		out.write("<tr>");
		out.write("<td colspan=2 class=text-right>");
		out.write("<a href=# class=\"btn  btn-xs btn-danger\">좋아요</a>"); // 빨강
		out.write("<a href=# class=\"btn  btn-xs btn-success\">찜하기</a>"); // 녹색
		out.write("<a href=# class=\"btn  btn-xs btn-info\">예약</a>");
		out.write("<a href=CamingListServlet class=\"btn  btn-xs btn-primary\">목록</a>"); // 파란색
		out.write("</td>");
		out.write("</tr>");

		out.write("</table>");
		out.write("</div>");
		out.write("<div class=col-sm-4>");
		out.write("</div>");
		out.write("</div>");
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
		// 자동으로 브라우저에서 읽어간다
		
	}

}
