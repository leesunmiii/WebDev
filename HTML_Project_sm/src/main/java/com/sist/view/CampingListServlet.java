package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Servlet => Server + let
// let => 가벼운 프로그램 => Applet / Midlet => 저장되는 메모리를 최소화 
/*
 *   서블릿 => 장점은 보안 => .java => .class
 *           단점 : HTML작성이 복잡하다 (문자열)
 *                 => 수정시마다 컴파일해야 된다 
 *           => HTML을 사용하지 않는다 
 *           => 자바와 HTML을 연결 
 *           => JSP + servlet
 *   JSP => 사용이 편리 , 보안이 취약 => .jsp(그대로)
 *                 => 컴파일 없이 실행 => 스크립트 
 *   M V C - Controller - Servlet
 *     -View => JSP
 *   Spring => 처리 => 웹 => Servlet
 *   =============================
 *   1. 페이징 (블록) 
 *   2. Cookie 
 *   3. Session
 *   4. 요청 = 응답 
 *   
 *   HTML : 정적 (화면 UI)
 *    => 서버로 데이터 전송 
 *       GET / POST / PUT / DELETE => RestFul
 *        |     | => id,pwd,데이터가 많은 경우 => <form>
 *       URL?데이터 
 *       노출/주로 사용 => 단순 데이터 전송 
 *                      페이지 / 번호 / 검색 ...
 *       => 전송이 없는 경우 : GET default
 *       
 *       javascript 
 *       ==========
 *       ajax({
 *         type:POST
 *         url:...
 *         success:function(result)
 *         {
 *         }
 *       })
 *       Vue,React
 *       axios.get...then()
 *       axios.post
 *   =================== CSS
 */
import java.util.*;
import com.sist.dao.*;
@WebServlet("/CampingListServlet")
public class CampingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //1. 전송방식 (HTML,XML) => 브라우저에 통보 
	   response.setContentType("text/html;charset=UTF-8");
	   //2. 브라우저에서 읽어갈 메모리 위치를 확보 
	   PrintWriter out=response.getWriter();
	   
	   //3. 사용자의 요청값을 받는다 
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1"; // default
	   int curpage=Integer.parseInt(page);
	   
	   //4. 데이터베이스 연동 => 요청한 데이터를 가지고 온다 
	   CampingDAO dao=CampingDAO.newInstance();
	   List<CampingVO> list=dao.CampingListData(curpage);
	   int totalpage=dao.CampingTotalPage();
	   // 쿠키 읽기 
	   List<CampingVO> cList=new ArrayList<CampingVO>();
	   Cookie[] cookies=request.getCookies();
	   try {
	   if(cookies!=null)
	   {
		   for(int i=cookies.length-1;i>=0;i--)
		   {
			   if(cookies[i].getName().startsWith("camping"))
			   {
				   String mno=cookies[i].getValue();
				  
				   CampingVO vo=dao.CampingDetailData(Integer.parseInt(mno));
				   
				   cList.add(vo);
			   }
		   }
	   }
	   }catch(Exception ex) {ex.printStackTrace();}
	   
	   // 블록 나누기 
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   // 1 => 현재 페이지 => 1~10
	   //             ==> (10-1)/10*10 = 0
	   //               11-1/10 => 1*10 => 10+1 ==> 11
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   // 10
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   
	   //5. 데이터를 HTML를 이용해서 출력 
	   out.write("<html>");
	   out.write("<head>"); // CSS / JavaScript => 없는 경우에는 생략이 가능
	   out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
	   out.write("<style type=text/css>");
	   out.write(".container{margin-top:50px}"); // 위쪽 여백
	   // margin:10px 10px 10px 10px 
	   //        top  right bottom left
	   out.write(".row{margin:0px auto;width:1000px}"); // 각 사진 가로 너비
	   out.write("</style>");
	   out.write("</head>");
	   
	   
	   
	   
	   
	   
	   
	   out.write("<body>"); // 화면 UI
	   out.write("<div class=container>");
	   
	   
	   
	   out.write("<div class=row>"); // 맛집 이미지
	   for(CampingVO vo:list)
	   {
		   out.write("<div class=\"col-md-4\">");
		   out.write("<div class=\"thumbnail\">");
		   out.write("<a href=CampingBeforeServlet?mno="+vo.getMno()+">");
		   out.write("<img src="+vo.getPoster()+" alt=\"Lights\" style=\"width:100%\">");
		   out.write("<div class=\"caption\">");
		   out.write("<p>"+vo.getTitle()+"</p>");
		   out.write("</div>");
		   out.write("</a>");
		   out.write("</div>");
		   out.write("</div>");
	   }
	   out.write("</div>");
	   out.write("<div class=row>"); // 페이지 출력 
	   
	   
	   
	   out.write("<div class=text-center>");
	   out.write("<ul class=\"pagination\">");
	   if(startPage>1)// startPage => 1 11 21 31...
	   {
	     out.write("<li><a href=CampingListServlet?page="+(startPage-1)+">&lt;</a></li>");
	     // 11page ==> 이전 ==> 10
	   }
	   for(int i=startPage;i<=endPage;i++)
	   {
	     out.write("<li "+(i==curpage?"class=active":"")+"><a href=CampingListServlet?page="+i+">"+i+"</a></li>");
	   }
	   if(endPage<totalpage)// 10 20 30..
	   {
	     out.write("<li><a href=CampingListServlet?page="+(endPage+1)+">&gt;</a></li>");
	   }
	   out.write("</ul>");
	   out.write("</div>");
	   out.write("</div>");
	   out.write("<div class=row>"); // 최근방문
	   if(cList.size()!=0)
	   {
		   for(CampingVO vo:cList)
		   {
			   out.write("<a href=CampingDetailServlet?mno="+vo.getMno()+">");
			   out.write("<img src="+vo.getPoster()+" style=\"width:100px;height:100px;margin:10px 0 0 5px;\">");
		       out.write("</a>");
		   }
	   }
	   else
	   {
		   out.write("<h3>방문한 기록이 없습니다</h3>");
	   }
	   out.write("</div>");
	   out.write("</div>");
	   out.write("</body>");
	   out.write("</html>");
	}

}






