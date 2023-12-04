<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%--
	JSP
	1. HTML + Java => ����
	2. ���� ���
	3. ������
		=page
		= taglib
		= include
	4. �ڹ� �ڵ�
		= ��ũ��Ʈ�� <% %> : �Ϲ� �ڹ� (�޼ҵ� ȣ��, ���, ��������)
		= ���� <%! %> : ���� : �������
		= ��¹� (ǥ����) : ȭ�� ��� => out.write()
		  <%= %>
	5. ���� ��ü : 9�� => �̸� ��ü�� ������ �Ŀ� ���
		***= request : ������� ��û ������ �����ϰ� �ִ�
		***= response : ���� ���� / ȭ�� ����
		***= session : 
		= out => <% %> , ${}
		***= application
		= config => web.xml
		= exception => try ~ catch
		= page => this
		***= pageContext
	6. JSP �׼� �±� / ����
		<jsp:~~>
	7. ǥ���� => EL ${} *************
	8. JSTL 
		=> �ڹ� ������ �±������� ����********
		=> <c:gofEach> <c:if> <c:choose>
		=> <fmt:formetDate> <fmt:formatNumber>
	9. MVC
	    ==========================
	   Model : Java
	   View : JSP
	   Controller : ���� (������: ����)
	   ================================
	   model 1 => ������ Ȩ������ => JSP
	   model 2 => ����Ʈ ���� => MVC
	  
	  1. �� ���� : request(��û) , response(����)
	  			 ============	=============
	  			 Client(Front)	Server(Back)
	  URL 
	  	http://localhost/JSPbasicProject_1/jsp/basic_1.jsp
	  	================ ==================================
	  		���� ����		
	  
	  	=> ���� ��û : ��쿡 ���󼭴� ���� Ȯ���ڸ� ����
	  				=> .ȸ��� => .naver, .do
	  	=> PathVariable / aaa /aaa => React
	  			 request
	  	client =========== Web Server ========== Web Container(WAS)
	  	=======				����ġ / IIS			===================
	  	������					|				| Tomcat  
	  						��û �޴´�					   |			
	  						���� ã�� => Servlet/JSP	  JSP/Servlet ����
							=> HTML/XML/JSON				|
								(�ڵ�ó��)					 HTML�� ��ȯ������
							<===========					|
								HTML�� �������� ����
								=> ������ ���� : �ҽ�����
				19 page ��û/���� => ��Ʈ��ũ (C/S)
						���� ������ : HTML/CSS => ������ ������ �Ұ���
						���� ������ : JSP  / JavaScript => ������ ����
						*** �������� ���� (������)
						*** ��û�� ���������� ���Ǵ� ��ġ
							========================
							| �ּҶ� : <a> <form>
								=> location.href
								=> ajax({url:...}) => jquery
								=> axios.get(url) => vue,react
								====== ������ ���� ======
								
		1. ����
					request
		client	=============== ��ü ����
								HttpServletRequest
									(request)
								HttpServletResponse
									(response)
										|
										------------- ���� ã��
														 |
													���� ��ü ����
													(new ~ Servlet())
														 |
													init() : ���ۿ� �ʿ��� ������ �б�
															=> web.xml
														 |
													doGet()/doPost()
													 GET	  POST
													 ---	 ------
													  |	        | ó��, (ȸ������, �α���) => ���� ����
													 ȭ���û/ �ܼ��� ������ ����
													 	=> Page, no, �˻���
													 =====================
													 ó�� => HTML�� ���� => �������� ���ؼ� Ŭ���̾�Ʈ�� ����
		1) ����
			�� ���� ����� ���ִ� �ڹ� Ŭ����
			=> �ڹ� �ȿ� HTML�ڵ带 ÷��
				out.write("<html>")..
			=> ����
				= HTML�� ����ϱ� ��ƴ� (�����ϴ�) => CSS / JavaScript
				= ���� ��Ⱑ ��ƴ�
				= �ڹ� = �ణ�� �����ص� �������� �ٽ� �ؾ��Ѵ�
				 => ��ư�� ���� ���� (���� ����) => ������
				 *** �����ڴ� �ٷ� ���� ����� �� �� �ִ� ���� ��ȣ
			   ����
			   	= �ڹ� => �ҽ��� �� �� ���� (.class) ������ �پ��
			   	= HTML�� ������� �ʴ� �� ����
			   		=> Controller (�ڹ�/HTML�� �и�) => ����
			   		   ========== �������� �̹� ���� (����)
			   		   = ���̺귯�� : ����
			   		   ��) 4���� => XML, 5���� => �����ϰ� �ڹ�
			   		   	=> ������(5), ����� (4)
			   		   	=> ������ ���� : Spring-Boot
		=> ������ ����
			=> ������ HTML/CSS/javaScript ����
			=> HTML�ȿ� �ڹٸ� ÷��
				=> ���� <% %> , <%= %>
			=> ������ �ϸ� ��Ĺ�� �������� �ڵ� ����
				=> �����ϰ� �������� ���ΰ�ħ�ϸ� �ٷ� ����
				=> ���� : ���ȿ� ����ϴ� (�ҽ� ��ü�� �����ؾ��ϱ⶧����)
				
		=> JSP�� ����	
			1) �������� ���� �ҽ� �ۼ��� �����ϴ� (������)
			2) HTML / Java�� �������ִ�
			3) �����ΰ� �ҽ� �ڵ��� ������ �۾��� �� �ִ�
			4) �ܼ��ϴ� => �����ͺ��̽� ���� => for / if
			5) = �ڹٰ� ���� ���� (�����͸� �о�´�) => HTML�� �̿��ؼ� ���
			6) HTML => out.write("<html>")
					  ========== ����
					  <html> �±װ� �ܵ����� ����
		=> ������ Ŭ������ �Ǿ��ִ�
		   JSP�� Ŭ������ �ƴ϶� �޼ҵ� �����̴�
		   ============================
		   
		   a.jsp => a_jsp.java => a_jsp.class => ����
		   				| ����
		   ����
		   a.jsp
		   <%@ page contentType="text/html;charset=UTF-8"%>
		   <!DOCTYPE html>
		   <html>
		   <head>
		   </head>
		   <body>
		   </body>
		   </html>
		   => ����ÿ� ��Ĺ�� ���� ����
		   => a.jsp.java
		   public class a_jsp extends HttpServlet
		   {
		   	=============================================
		   		������� <%! %>
		   		�޼ҵ尡 ���� ��ġ
		   	=============================================
		   }
		   	public void _jspinit()
		   	{
		   		������ ����
		   		��������� �ʱ�ȭ
		   	}
		   	public void _jspDestory()
		   	{
		   		// ���ΰ�ħ, ȭ�� �̵� => �ڵ����� �޸� ����
		   	}
		   	public void _jspService(HttpServletRequest,HttpServletResponse response)
		   	{
		   		response.setContentType("text/html;charset=UTF-8")
		   		//PrintWrite out�� JspWriter out;��
		   		JspWriter out;
		   		HttpSession session
		   		Object page=this;
		   		PageContext pageContext=ServletContext
		   		================================================
		   		<% %>
		   		out.write("<!DOCTYPE html>")
		  		out.write("<html>")
		  		out.write("<head>")
		  		out.write("</head>")
		   		out.write("<body>")
		   		out.write("</body>")
		  		out.write("</html>")
		  		 ============================================
		   	}
		   }										 
		   69page ���� ����
		   1. ������������ URL�� ��û
		   2. �� ������ ��û�� �޴´�
		   3. .html, .css, .json => �������� ��ü���� ó�� �� �������� ����
		   		=> jsp,servlet... ������ �Ұ���
		   		   -----------
		   		   	|
		   		   ������ ��û => ��Ĺ (resin, ���콺, ������, �������...)
		   		   			   ====================== �̷��� �� ��Ƽ� WAS���� ��
		   		   			   Web Application Server
		   		   	|
		   		   1) �ڹٷ� ���� => (jsp���ϸ�) _jsp.java
		   		   2) �ڹٸ� ������ => ����
		   		   3) �ڹ� ����
		   		   	  =======
		   		   	  | �������� �о �޸𸮿� html�� ������ش�
		   		   	  	================== ��� ��Ʈ��
		   		   4) ��µ� �޸𸮷κ��� html�� ���پ� �о �������� ���
		   		   					================= ���������� ���
		   	71page => jsp�� �����ֱ�
		   	=> �ڵ�ȣ��
		   		_jspInit() : �ʱ�ȭ (web.xml ��� �ÿ� �о��)
		   			|
		   		_jspService() : JSP���� �ڵ��� �Ǵ� ����, ȭ��/����� ���
		   			|
		   		_jspDestory() : ���ΰ�ħ / ȭ�� �̵� �ڵ����� �޸� ����
		   		
		   		_jspService()
		   		{
		   			===========================
		   			
		   			============================JSP����
		   		}
		   		   
		   
		   <% %> ���� �ڹ� �ڵ��� �ϸ� => �Ϲ� �ؽ�Ʈ�� �ν�
		   
	--%>
	<%--
		public void _jspService(request, response)
		{
		
			============ JSP�ҽ� �ڵ� ================
			
			
			========================================
		}	
	 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	String name="ȫ�浿";
	out.write(name);
</body>
</html>