package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;


@WebServlet("/ReplyUpdateServlet")
public class ReplyUpdateServlet extends HttpServlet {
	/*
	 * https://www.google.com/search?q=%EC%9E%90%EB%B0%94&sca_esv=586873451&sxsrf=AM9HkKkcdT_koga2gujPhkiPchE80PzBPw%3A1701415429955&ei=BYppZerjOf2R2roPrPuf2AE&ved=0ahUKEwiq-sSy2u2CAxX9iFYBHaz9BxsQ4dUDCBA&uact=5&oq=%EC%9E%90%EB%B0%94&gs_lp=Egxnd3Mtd2l6LXNlcnAiBuyekOuwlDIHECMYsAMYJzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwAzIKEAAYRxjWBBiwA0jqA1AAWPIBcAF4AZABAZgB5wGgAaUEqgEFMS4yLjG4AQPIAQD4AQHCAgoQIxiABBiKBRgnwgIEECMYJ8ICChAAGIAEGIoFGEPCAgsQABiABBixAxiDAeIDBBgAIEHiAwUSATEgQIgGAZAGCg&sclient=gws-wiz-serp
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// rno , gno , typeno , msg  ==> GoodsDetail에 160번째줄 out.write("<form method=post action=ReplyUpdateServlet>"); 밑에 있는것들 보고
		// msg가 한글로 넘어왔기때문에 디코딩해야함
		// 인코딩 => 바이트를 잘라서 1바이트로 왔음, 우리는 2바이트로 사용해야함
		//브라우저는 1바이트로 읽을수있기때문에 (한글은 2바이트) 한글을 인코딩해서 사용.
		
		try
		{	
			// 전송할 때는 자바 => %EC%9E%90%EB%B0%94(인코딩)
			// 수신할 때는 %EC%9E%90%EB%B0%94 => 자바 (디코딩)
			request.setCharacterEncoding("UTF-8"); //디코딩 하는, 원상복구하는 코드 
		}catch(Exception ex) {}
		 String rno=request.getParameter("rno"); // 앞에는 상관없고 뒤에만 맞추면 됨
		 String gno=request.getParameter("gno"); // a태그로 보낼때는 ?= 이 값들과 맞춰야함
		 String tno=request.getParameter("typeno");
		 String msg=request.getParameter("msg");
		 
		 // DAO => 수정
		 ReplyDAO dao=ReplyDAO.newInstance();
		 dao.replyUpate(Integer.parseInt(rno), msg);
		 // 화면 => Detail로 이동
		 response.sendRedirect("MainServlet?mode=5&no="+gno+"&type="+tno); // GoodsDetailServlet을 보여줘라
	}

}









