<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
	for => for-each ����
	=> if~else
	
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%--
		1~100������ ��, ¦����, Ȧ���� �� 
	--%>
	<%
		// Ȱ��ȭ, ��Ȱ��ȭ
		int sum=0,even=0,odd=0;
			for(int i=1;i<=100;i++)
			{
				sum+=i;
				if(i%2==0)
					even+=i;
				else
					odd+=i;
			}
	%>
	<h3>1~100������ ����:<%=sum %></h3>
	<h3>1~100������ ¦������:<%=even %></h3>
	<h3>1~100������ Ȧ������:<%=odd %></h3>
</body>
</html>