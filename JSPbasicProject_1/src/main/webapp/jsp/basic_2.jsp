<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--
	=> ������ ȭ�� ���(jsp) => ThymeLeaf (html)�� �ٲ��
	113page
	=======
		1. �ڹ� / HTML�� �и�
		= �ڹ� �ڵ��ϴ� ��� (��ũ��Ʈ��)
			1) <% %> : ��ũ��Ʈ��
				�Ϲ� �޼ҵ� �ȿ��� �ڵ� => ����(��������), �޼ҵ� ȣ��, ���, ������
				=> _jspService()�ȿ� ÷��
				=> �ڹٿ� ����
				=> ������ ������ ;
				=> <%
						���⼭ �ּ�
						/*
						*/
						// 
					%>
			2) <%! %> : ���� => �޼ҵ� ����, �������
				=> Ŭ���� ������ ����
			3) out.write() => �ڹ� ������ ���
			   ===========
			   String name="";
			   	<%= name%> : => out.write(==============);
			1. JSP => ������ �б�
					=> �����ͺ��̽� ����
			2. HTML ����
			3. �ʿ��� ��ġ�� for/if�� �̿��ؼ� ȭ�� ���
			4. CSS�� ����
			5. �������� ���� : Javascript
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <h1>&lt;% ��ũ��Ʈ�� %&gt;</h1>
 <h3>������</h3>
 <table border=1 width=700>
 	<tr>
 	<%
 	for(int i=2;i<=9;i++)
 	{
 	%>
 	<th><%= i+"��" %></th>
 	<%
 	}
 	%>
 	</tr>
 	<%
 		for(int i=1;i<=9;i++)
 		{
 			//�ּ�
 			/*
 			������ �ּ�
 			*/
 	%>
 		<tr>
 	<%
 			for(int j=2;j<=9;j++)
 			{
 	%>
 	<td><%= j+"*"+i+"="+(j*i)%></td>
 	<%	
 			}
 		%>
 		</tr>
 		<%
 		}
 	%>
 </table>
</body>
</html>