<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
	public class basic_3_jsp
	{
		========================
		=> �޼ҵ� ����, ������� <%! %>
		========================
		public void _jspService()
		{
		=========================
		<%
		%>
		** <html> => �ڵ����� out.write("<html>")
		==========================
	}
--%>
<%! 
	// �������� (�������), �޼ҵ� ���� => ���� ���󵵰� ����
	// �ڹ� ��ũ��Ʈ VueJS => data:{}, React => state:{}
	String name="ȫ�浿";
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