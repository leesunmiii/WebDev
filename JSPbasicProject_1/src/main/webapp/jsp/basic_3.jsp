<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
	public class basic_3_jsp
	{
		========================
		=> 메소드 선언, 멤버변수 <%! %>
		========================
		public void _jspService()
		{
		=========================
		<%
		%>
		** <html> => 자동으로 out.write("<html>")
		==========================
	}
--%>
<%! 
	// 전역변수 (멤버변수), 메소드 선언 => 거의 사용빈도가 없다
	// 자바 스크립트 VueJS => data:{}, React => state:{}
	String name="홍길동";
	public String getName()
	{
		return name;
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1><%= getName() %>
</body>
</html>