<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" buffer="16kb"%>
<%--
	139page
	= ������
		page : jsp���Ͽ� ���� ����
		=> JSP������
		=> 1. contentType = ����� ����� ���� ����
				=> �ڹٷ� ���� : response.setContentType()
				=> html => text/html;charset=UTF-8 (�������� �ѱ� ����)
									 =============
									 charset=ISO-8859-1 (�̰� �⺻ ��)
				   xml => text/xml;charset=UTF-8
				   json => text/plain;charset=UTF-8
				   	=> �������� �˷��ش�
				   	=> page�ȿ��� 1���� ����� ����
		   2. import => �ܺ� ���̺귯�� ÷���� �� ���
		   				java.lang, javax.http.servlet
		   				============================= ���� ����
		   			 => ������� => 2
		   			 <%@ page import="java.util.*,java.io.*"%>
		   			 <%@ page import="java.util.*">
		   			 <%@ page import="java.io.*"%>
		   3. buffer => html�� �����ϴ� �޸� ����
		   			 => 8kb
		   			 => html�� ����� �� �� �뷮�� �����ÿ��� ����
		   			 	buffer="16kb"
		   4. errorPage
		   
		taglib : �±׷� �ڹ� �⺻ ������ ���� => jstl/el
		include : JSP�ȿ� �ٸ� JSP�� ÷��
		<%@ �����ڸ� �Ӽ�="��"
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>