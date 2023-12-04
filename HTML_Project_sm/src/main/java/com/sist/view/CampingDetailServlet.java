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
		// 사용자 보내준 데이터 받기 
		String mno=request.getParameter("mno");
		// mno에 해당하는 데이터 정보를 가지고 온다 => CampingDAO
		// 최근 방문 
		
		// 데이터베이스 연동 
		CampingDAO dao=CampingDAO.newInstance();
		CampingVO vo=dao.CampingDetailData(Integer.parseInt(mno));
		
		// 읽어온 데이터를 HTML에 출력 
		   out.write("<html>");
		   out.write("<head>"); // CSS / JavaScript => 없는 경우에는 생략이 가능
		   out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		   
		   out.write("<style type=text/css>");
		   out.write(".container{margin-top:50px}");
		   // margin:10px 10px 10px 10px 
		   //        top  right bottom left
		   out.write(".centerfont{font-size: 12px;}");
		   out.write("a{margin-left:5px}");
		   out.write("</style>");
		   out.write("</head>");
		   out.write("<body>"); // 화면 UI
		   
		   
		   out.write("<div class=container>");
		   
//		   여기부터 위쪽=======================================================================================================================

		   out.write("<h3>"+vo.getTitle()+"&nbsp;<span style=\"color:orange\"></span></h3>");
		   out.write("</tr>");
		   
		   out.write("<tr>");
		   out.write("<h5><span style=\"color:orange\">"+vo.getSubtitle()+"&nbsp;<span style=\"color:black\"></span></h5>");
		   out.write("</tr>");
		   
//         여기부터 왼쪽=====================================================================================================================
		   out.write("<div class=row>");
		   out.write("<tr>");
		   out.write("<div class=col-sm-4>");
		   out.write("<tr>"); // 한줄
		   out.write("<img src="+vo.getPoster()+" style=\"width:100%\" class=img-rounded>");
		   out.write("</tr>");
		   out.write("</div>");

//			여기부터 가운데======================================================================================================================
		   out.write("<div class=col-sm-4>"); // 8 하면 밑으로 내려감 
		   out.write("<table class=table>");
		   
		   out.write("<tr>");
		   out.write("<th width=35% class='centerfont'>주소</th>");
		   out.write("<td width=65% class='centerfont'>"+vo.getLoc()+"</td>");
		   out.write("</tr>");
		   
		   out.write("<tr>");
		   out.write("<th width=35% class='centerfont'>문의처</th>");
		   out.write("<td width=65% class='centerfont'>"+vo.getNum()+"</td>");
		   out.write("</tr>");
		   
		   out.write("<tr>");
		   out.write("<th width=35% class='centerfont'>캠핑장 환경</th>");
		   out.write("<td width=65% class='centerfont'>"+vo.getEnvir()+"</td>");
		   out.write("</tr>");
		   
		   out.write("<tr>");
		   out.write("<th width=35% class='centerfont'>캠핑장 유형</th>");
		   out.write("<td width=65% class='centerfont'>"+vo.getCategory()+"</td>");
		   out.write("</tr>");
		   
		   out.write("<tr>");
		   out.write("<th width=35% class='centerfont'>운영기간</th>");
		   out.write("<td width=65% class='centerfont'>"+vo.getSeason()+"</td>");
		   out.write("</tr>");
		   
		   out.write("<tr>");
		   out.write("<th width=35% class='centerfont'>운영일</th>");
		   out.write("<td width=65% class='centerfont'>"+vo.getOpenclose()+"</td>");
		   out.write("</tr>");
		   

			out.write("<tr>");
			out.write("<th width=35% class='centerfont'>홈페이지</th>");
			out.write("<td width=65% class='centerfont'>"+vo.getHomepage()+"</td>");
			out.write("</tr>");
			
			out.write("<tr>");
			out.write("<th width=35% class='centerfont'>예약방법</th>");
			out.write("<td width=65% class='centerfont'>"+vo.getReserve()+"</td>");
			out.write("</tr>");
			
			out.write("<tr>");
			out.write("<th width=35% class='centerfont'>주변이용가능시설</th>");
			out.write("<td width=65% class='centerfont'>"+vo.getFacility()+"</td>");
			out.write("</tr>");
		   
		   
		   out.write("<tr>");
		   out.write("<td colspan=2 class=text-right>");
		   out.write("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
		   out.write("<a href=# class=\"btn btn-xs btn-success\">찜하기</a>");
		   out.write("<a href=# class=\"btn btn-xs btn-info\">예약</a>");
		   out.write("<a href=CampingListServlet class=\"btn btn-xs btn-primary\">목록</a>");
		   out.write("</td>");
		   out.write("</tr>");
		   
		   out.write("</table>");
		   out.write("</div>");
		   
// 			여기부터 오른쪽========================================================

		   out.write("<div class=col-sm-4>");
		   out.write("<tr>");
		   out.write("<img src="+vo.getPoster()+" style=\"width:100%\" class=img-rounded>");
		   out.write("</tr>");
		   out.write("</div>");
		   out.write("</div>");
//			========================================================
		   out.write("<div class=row>");
		   out.write("<hr/>"); // 구분선
		   out.write("<div class=col-sm-4>");
		   out.write("<img src=https://gocamping.or.kr/"+vo.getPic1()+" style=\"width:100%\" class=img-rounded>");
		   out.write("</div>");
		   
		   out.write("<div class=col-sm-4>");
		   out.write("<img src=https://gocamping.or.kr/"+vo.getPic2()+" style=\"width:100%\" class=img-rounded>");
		   out.write("</div>");
		   
		   out.write("<div class=col-sm-4>");
		   out.write("<img src=https://gocamping.or.kr/"+vo.getPic3()+" style=\"width:100%\" class=img-rounded>");
		   out.write("</div>");
		   out.write("</div>");
		   
//			========================================================
		   out.write("<div class='row' style='word-break: keep-all;'>");
		   out.write("<hr/>"); // 구분선
		   out.write("<p>" + vo.getExplain() + "</p>");
		   out.write("</div>");
//			========================================================
		   
		   out.write("</div>");
		   out.write("</div>");
		   out.write("</body>");
		   out.write("</html>");
		// 자동으로 브라우저에서 읽어 간다
	}

}
	