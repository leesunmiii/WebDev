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

@WebServlet("/GoodsDetailServlet")
public class GoodsDetailServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=UTF-8");
      // ISO-8859_1
      PrintWriter out=response.getWriter();
      // 클라이언트가 보낸 데이터 받기
      // MainServlet?mode=5&no="+vo.getNo()+"&type=1
      // ================== ========================
      //    mode => 화면변경     DetailServlet
      String no=request.getParameter("no");
      String type=request.getParameter("type");
      System.out.println(no+","+type);
      // DAO연동
      GoodsDAO dao=GoodsDAO.newInstance();
      GoodsVO vo=dao.goodsDetailData(Integer.parseInt(no), Integer.parseInt(type));
      // 화면 출력
      out.write("<html>");
      out.write("<head>");
      out.write("<link rel=\"stylesheet\" href=\"css/style.css\">");
      out.write("<script type=text/javascript src=\"http://code.jquery.com/jquery.js\"></script>");
      out.write("<script type=text/javascript>");
      out.write("let i=0;");
      // 자바 스크립트 => 자동 변환 변수 => let i=0 i:int
      // let a="aaa" a:String let d=10.5 => d:Double
      // let o={} => o : Object, let k=[] k:Array
      // var => scope
      out.write("$(function(){"); // 수정 누르면 수정칸 나왔다가 다시 누르면 사라지는...
      out.write("$('.ups').click(function(){");
      out.write("$('.updates').hide();");
      out.write("let a=$(this).attr('data-no');"); // this:클릭한 대상
      out.write("if(i==0){");
      out.write("$('#m'+a).show();");
      out.write("$(this).text('취소');");
      out.write("i=1");
      
      out.write("}else{");
      out.write("$('#m'+a).hide();");
      out.write("$(this).text('수정');"); // 수정 버튼 누르면 취소 버튼으로 바뀌고 취소 누르면 수정칸 사라지는...
      
      out.write("i=0;");
      
      out.write("}");
      out.write("})");
      out.write("})");

      out.write("</script>");
      out.write("</head>");
      out.write("<body>");
      
      String html="<div class=\"row\">"
            + "         <table class=\"table\">"
            + "            <tr>"
            + "               <td width=\"35%\" align=\"center\" rowspan=\"9\">"
            + "                  <img src="+vo.getPoster()+" id=\"image\">"
            + "               </td>"
            + "               <td width=\"65%\" align=\"center\">"
            + "                  <span id=\"title\">"
            + vo.getName()                     
            + "                  </span>"
            + "               </td>"
            + "            </tr>"
            + "            <tr>"
            + "               <td width=\"65%\">"
            + "                  <span id=\"sub\">세계 최초 IH 방식 가열식 스팀 가습기! 인덕션 방식으로 빠르게 끓여 살균하고, 유해한 잔여물 없이 순수한 물만을 가습해요!</span>"
            + "               </td>"
            + "            </tr>"
            + "            <tr>"
            + "               <td width=\"65%\">"
            + "                  <span id=\"percent\">"+vo.getDiscount()+"</span>&nbsp;"
            + "                  <span id=\"price\">"+vo.getPrice()+"</span>"
            + "                  <p>"
            + "                     <del id=\"psub\">398,000원</del>"
            + "                  </p>"
            + "               </td>"
            + "            </tr>"
            + "            <tr>"
            + "               <td width=\"65%\">"
            + "                  <span id=\"label\">첫구매할인가</span>"
            + "                  <span id=\"price2\">"+vo.getFprice()+"</span>"
            + "               </td>"
            + "            </tr>"
            + "            <tr>"
            + "               <td width=\"65%\">"
            + "                  <span id=\"star\">★★★★★</span>&nbsp;"
            + "                  <span id=\"bold\">4.5</span>"
            + "                  <span id=\"count\">(299)</span>"
            + "               </td>"
            + "            </tr>"
            + "            <tr>"
            + "               <td width=\"65%\">"
            + "                  <img src=\"https://recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png\">&nbsp;&nbsp;&nbsp;&nbsp;"
            + "                  <span id=\"delivery\">"+vo.getDelivery()+"</span>"
            + "               </td>"
            + "            </tr>"
            + "            <tr>"
            + "               <td width=\"65%\">"
            + "                  <select id=\"sel\">"
            + "                     <option>옵션 선택</option>"
            + "                  </select>"
            + "               </td>"
            + "            </tr>"
            + "            <tr>"
            + "               <td width=\"65%\">"
            + "                  <input type=\"button\" value=\"장바구니\" id=\"cart\">"
            + "                  <input type=\"button\" value=\"바로구매\" id=\"buy\">"
            + "               </td>"
            + "            </tr>"
            + "         </table>"
            + "      </div>";
      out.write(html);
      
      out.write("<div style=\"height:30px\"></div>");
      out.write("<div class=row>");
      // 댓글 출력
      out.write("<table class=table>");
      out.write("<tr>");
      out.write("<td>");
      ReplyDAO rdao=ReplyDAO.newInstance();
      List<ReplyVO> list=rdao.replyListData(Integer.parseInt(type), Integer.parseInt(no));
      HttpSession session=request.getSession();
      String id=(String)session.getAttribute("id");
      for(ReplyVO rvo:list)
      {
    	  out.write("<table class=table>");
          out.write("<tr>");
          out.write("<td class=text-left>");
          out.write("◑"+rvo.getName()+"&nbsp;("+rvo.getDbday()+")");
          out.write("</td>");
          out.write("<td class=text-right>");
          if(rvo.getId().equals(id))
          {
			out.write("<span class=\"btn btn-xs btn-success ups\" data-no="+rvo.getRno()+">수정</span>&nbsp;");
			out.write("<a href=ReplyDeleteServlet?rno="+rvo.getRno()+"&type="+type+"&no="+no+" class=\"btn btn-xs btn-info\">삭제</a>");
          }
          out.write("</td>");
          out.write("</tr>");

      
      out.write("<tr>");
      out.write("<td colspan=2>"); // 댓글 내용 넣을거임
      out.write("<pre style=\"white-space:pre-wrap;background-color:white;border:none\">"+rvo.getMsg()+"</pre>");
      out.write("</td>");
      out.write("</tr>");
      //댓글 수정
      out.write("<tr id=m"+rvo.getRno()+" class=updates style=\"display:none\">"); // 수정하기 칸 안보이게 한 것
      out.write("<td colspan=2>");
      
      out.write("<form method=post action=ReplyUpdateServlet>");
      out.write("<input type=hidden name=rno value="+rvo.getRno()+">"); // rno:댓글의 고유번호
	  out.write("<input type=hidden name=gno value="+no+">");
	  out.write("<input type=hidden name=typeno value="+type+">");
	  out.write("<textarea name=msg rows=4 cols=100 style=\"float:left\">"+rvo.getMsg()+"</textarea>");
	  out.write("<input type=submit value=\"댓글수정\" style=\"width:100px;height:92px;background:blue;color:white\">"); //공백이 들어가거나 문자가 길어지면 무조건 \"넣어야함
	  out.write("</form>");
	  // 처리하는 서블릿(JSP) / 화면 출력 서블릿 (JSP)
	  // => 화면이 없는 (HTML) => 자체에서 처리
      out.write("</td>");
      out.write("</tr>");
      out.write("</table>");
      }
      // 댓글을 작성하는 위치 (로그인이 되면 댓글을 작성하게)
      if(id!=null) // 로그인이 되었다면
      {	  
    	  out.write("<form method=post action=GoodsDetailServlet>");
    	  out.write("<input type=hidden name=gno value="+no+">");
    	  // hidden 잘 기억하기
    	  out.write("<input type=hidden name=typeno value="+type+">");
    	  
    	  out.write("");
    	  out.write("<textarea name=msg rows=4 cols=100 style=\"float:left\"></textarea>");
    	  //										  ========== 옆에 붙여라
    	  out.write("<input type=submit value=\"댓글쓰기\" style=\"width:100px;height:92px;background:blue;color:white\">"); //공백이 들어가거나 문자가 길어지면 무조건 \"넣어야함
    	  out.write("</form>");
      }
      out.write("</div>");
      out.write("</body>");
      out.write("</html>");
      // => head, style, script => 닫기를 하지 않는 경우에는 브라우저가 흰색 화면
   }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   try
   {
	   req.setCharacterEncoding("UTF-8");
	   
   }catch(Exception ex) {}
   
	   String gno=req.getParameter("gno");
	   String typeno=req.getParameter("typeno");
	   String msg=req.getParameter("msg");
	   System.out.println("gno="+gno+",type="+typeno);
	   HttpSession session=req.getSession();
	   String id=(String)session.getAttribute("id");
	   String name=(String)session.getAttribute("name");
	   
	   ReplyVO vo=new ReplyVO();
	   vo.setId(id);
	   vo.setName(name);
	   vo.setMsg(msg);
	   vo.setGno(Integer.parseInt(gno));
	   vo.setTypeno(Integer.parseInt(typeno));
	   
	   //DAO 연동
	   ReplyDAO dao=ReplyDAO.newInstance();
	   dao.replyInsert(vo);
	   
	   // 이동(화면 이동)
	   resp.sendRedirect("MainServlet?mode=5&no="+gno+"&type="+typeno);
	   /*
	    * request : 클라이언트에 대한 정보
	    * 			ip / port...
	    * 			사용자 정보 전송 => 전송한 모든 정보는  request
	    * response : 응답 정보 / 헤더정보
	    * 			=========
	    * 			| HTML => setContentType
	    * 			화면 이동 정보 => sendResirect()
	    * 
	    * =>JSP 동일
	    * 	내장 객체 => request, response, session
	    * 	=> Spring에서는 JSP동일
	    */
   }
}