package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet => Server + let => 서버에서 실행되는 가벼운 프로그램이라는 뜻
// let => 가벼운 프로그램, 메모리 할당을 작게 해주는 => Applet / Midlet(핸드폰에 들어가는 프로그램) => 저장되는 메모리를 최소화
/*
 *  Servlet의 장점 : 보안이 좋다 => .java 파일이 아니라 .class 파일 => 아무도 소스를 보지못한다
 *  		 단점 : HTML 작성이 복잡하다. (out.write 안에 넣어야하니)
 *  			   수정시마다 컴파일해야한다
 *  			   => HTML을 사용하지 않는다
 *  			   => 자바와 HTML을 연결해주는 역할만 수행
 *  			   => JSP + Servlet
 *  JSP 파일 => 사용이 편하다, 보안이 취약 => .jsp 파일이다 => 컴파일된 파일이 아니어서 소스 그대로 줘야함 => 소스 노출
 *  		=> 컴파일 없이 실행할 수 있게 만들어짐 => 바로 갱신이 되게 => 컴파일없이 진행되는걸 스크립트라 함
 *  
 *  서브렛이 JSP로 바뀌어가느 추세
 *  서브렛에서는 HTML 출력안함. 지금은 JSP안배워서 여기서 추력하는것
 *  
 *  M V C - Controller => Servlet
 *    - View => JSP
 *    Spring 동작을 해주는게 스프링 => 처리 => 웹 => Servlet
 *    ================================================
 *    [알아둘것들]
 *    1. 페이징 기법 (블록)
 *    2. Cookie
 *    3. Session
 *    4. 요청 = 응답
 *    
 *    HTML : 화면 출력 역할만 수행. (화면UI) => 정적이다
 *    		 디자인이 투박하니 CSS(디자인)와 결합해 사용
 *    	=> 서버로 데이터 전송
 *    	GET 							/ POST / PUT / DELETE => 이렇게 연결하는 과정을 RestFul이라함
 *    	 |      							| => id, pwd,데이터가 많은 경우 => <form> ,,,form태그 외에는 전부 get방식 
 *    URL?데이터									PUT / DELETE는 스프링에 나옴
 *    ========
 *    노출이 심하다는 단점
 *    주로 사용은 단순한 데이터
 *    			==========
 *    			페이지/번호/검색어...
 *    
 *    	=> 전송이 없는 경우 (데이터를 안보내는 경우) (네이버 들어갈 떄, 다음 들어갈때)
 *    시작하자마자 들어가는 경우 : GET default
 *    
 *    javascript
 *    ==========
 *    ajax({
 * 		type:POST
 * 		url:...
 * 		success:function(result)
 * 		{
 * 		}   
 *    })
 *    
 *    Vue,React => 주고 받고를 동시에 처리 => 서버 깜빡거리지않음
 *    axios.get...then()
 *    axios.post
 *    	
 *   
 *    
 */
import java.util.*;
import com.sist.dao.*;
@WebServlet("/CampingListServlet")
public class CampingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	톰캣이 유알엘 주소(/FoodListServlet) 받아서 구동// 유알엘 주소를 보고 구동해주는것
		//	톰캣안에 메인이 들어가있음
		
		//	1. 전송방식 (HTML,XML) => 브라우저에 통보
		response.setContentType("text/html;charset=UTF-8");
		//	2. 브라우저에서 읽어갈 메모리 위치를 확보
		PrintWriter out=response.getWriter();
		
		//	3. 사용자의 요청값을 받는다
		String page=request.getParameter("page");
		if(page==null)
			page="1"; // default
		int curpage=Integer.parseInt(page);
		
		//	4. 데이터베이스 연동 => 요청한 데이터를 가지고 온다
		CampingDAO dao=CampingDAO.newInstance();
		List<CampingVO> list=dao.CampingListData(curpage);
		int totalpage=dao.CampingTotalPage();
		
		//	쿠키 읽기
		List<CampingVO> cList=new ArrayList<CampingVO>();
		Cookie[] cookies=request.getCookies();
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
		//	블록 나누기 (페이지를 1부터 10까지 출력)
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		// 1 => 현재페이지 => 1~10
		//				=> (10-1)/10*10 = 0 
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// 10
		if(endPage>totalpage)
			endPage=totalpage;
		
		//	5. 데이터를 HTML 이용해서 출력
		out.write("<html>");
		out.write("<head>"); // CSS나 JavaScript가 있는 경우 <head>태그 사용 => 없는 경우에는 생략가능
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.write("<style type=text/css>");
		out.write(".container{margin-top:50px}");
		// margin:10px 	10px 	10px 	10px
		// 		  top	right	bottom	left
		out.write(".row{margin:0px auto;width:900px}"); // 모든 row는 가운데 정렬해라
		out.write("</style>");
		out.write("</head>");
		out.write("<body>"); // 화면 UI가 나오는 부분
		out.write("<div class=container>");
		out.write("<div class=row>"); // 맛집 이미지
		for(CampingVO vo:list)
		{
			out.write("<div class=\"col-md-3\">");
			out.write("<div class=\"thumbnail\">");
			out.write("<a href=CampingBeforeServlet?mno="+vo.getMno()+">"); // 클릭할 때 상세보기 넘어가는 (링크 걸리는 위치)
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
		if(startPage>1) // startPage => 1 11 21 31 ...
		{	
			out.write("<li><a href=CampingListServlet?page="+(startPage-1)+">&lt;</a></li>");
			// 11page => 이전 => 10
		}
		for(int i=startPage;i<endPage;i++) // 10 20 30
		{
			out.write("<li "+(i==curpage?"class=active":"")+"><a href=CampingListServlet?page="+i+">"+i+"</a></li>");
		}
		if(endPage<totalpage)
		{
			out.write("<li><a href=CampingListServlet?page="+(endPage+1)+">&gt;</a></li>");
		}
		
		out.write("</ul>");
		out.write("</div>");
		out.write("</div>");
		out.write("<div class=row>"); // 최근 방문
		if(cList.size()!=0)
		{
			for(CampingVO vo:cList)
			{
				out.write("<a href=CampingDetailServlet?mno="+vo.getMno()+">");
				out.write("<img src="+vo.getPoster()+" style=\"width:100px;height:100px;margin:10px 0 0 5px;\">");
				//																		== margin-top:10px;margin-left:5px
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
