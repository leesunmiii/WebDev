package com.sist.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap=new HashMap();
	
	public void init(ServletConfig config) throws ServletException {
		String path="C:\\Users\\hjug\\eclipse-workspace\\JSPMVCProject_1213\\src\\main\\webapp\\application.xml";
		try
		{
					DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
					DocumentBuilder db=dbf.newDocumentBuilder();
					
					//	파서기
					Document doc=db.parse(new File(path));
					
					// 1. 루트 (beans) => 테이블명
					Element beans=doc.getDocumentElement();
					//System.out.println(beans.getTagName());
					// 2. 같은 태그를 묶어서 제어
					NodeList list=beans.getElementsByTagName("bean");
					for(int i=0;i<list.getLength();i++)
					{
						Element bean=(Element)list.item(i);
						String id=bean.getAttribute("id");
						String cls=bean.getAttribute("class");
						System.out.println(id+"=>"+cls);
						// 메모리 할당  => Map에 저장
						Class clsName=Class.forName(cls);
						Object obj=clsName.getDeclaredConstructor().newInstance();
						System.out.println(obj);
						clsMap.put(id, obj);
						
					}
					
		}catch(Exception ex) {}
	}
	//	=> 메뉴 제작
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 주문 받기 시작
			String cmd=request.getRequestURI();
			cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
			Model model=(Model)clsMap.get(cmd);
			String jsp=model.handleRequest(request);
			if(jsp.startsWith("redirect:"))
			{
				jsp=jsp.substring(jsp.indexOf(":")+1);
				response.sendRedirect(jsp);
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher(jsp);
				rd.forward(request, response);
			}
		
		
		
		
		
		
	}

}
