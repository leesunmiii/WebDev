<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,com.sist.dao.*"%>
<%--    
<%@ page import="java.util.*" %>
<%@ page import="com.sist.dao.*" %> 
--%>
<%--
	1. ������ �б�
		1) import�� ����
		<%@ import="java.util.*, com.sist.dao.*"%>
		
		<%@ import="java.util.*"%>
		<%@ import="com.sist.dao.*"%>
	2. html�� �ش� ��ġ�� <% %> => for
	   => ������ �����  <%= %>
	   => �߽��� View (ȭ�� ���)
--%>
<%
	EmpDAO dao=EmpDAO.newInstance();
	List<EmpVO> list=dao.empListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/table.css">
<style type="text/css">
	.container{
	margin-top: 50px;
	width:100%;
	margin:0px auto;
	}
	h1{
		text-align:center;
	}
	.row{
	width:1024px;
	margin:0px auto; /*��� ����*/
	}
</style>
</head>
<body>
<div class="container">
	<div class="row">
	<h1>��� ���</h1>
	<table class="text-content" width=800>
		<tr>
			<th>���</th>
			<th>�̸�</th>
			<th>����</th>
			<th>�Ի���</th>
			<th>�޿�</th>
		</tr>
		<%--
		
			<%= ��¹� %> : out.println(=====)
										| ÷�� => ;�� ����ϸ� ���� �߻�
			= �ڹ� / HTML�� ����
			= ��� �ҽ��� _jspService()�� ÷�� => ��Ĺ�� �ڵ� ó��
				��Ĺ : WAS => JSP\Servlet ����
						������� : GIT
		--%>
		<%
			for(EmpVO vo:list)
			{
		%>
			<tr class="dataTr">
				<td class="text-center"><%=vo.getEmpno() %></td>
				<td class="text-center"><%=vo.getEname() %></td>
				<td class="text-center"><%=vo.getJob() %></td>
				<td class="text-center"><%=vo.getHiredate().toString() %></td>
				<td class="text-center"><%=vo.getSal() %></td>
			</tr>
	</table>
	</div>
	</div>
</body>
</html>