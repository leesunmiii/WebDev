<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" buffer="16kb"%>
<%--
	139page
	= 지시자
		page : jsp파일에 대한 정보
		=> JSP시작점
		=> 1. contentType = 실행시 변경될 파일 형식
				=> 자바로 변경 : response.setContentType()
				=> html => text/html;charset=UTF-8 (안적으면 한글 깨짐)
									 =============
									 charset=ISO-8859-1 (이게 기본 값)
				   xml => text/xml;charset=UTF-8
				   json => text/plain;charset=UTF-8
				   	=> 브라우저에 알려준다
				   	=> page안에서 1번만 사용이 가능
		   2. import => 외부 라이브러리 첨부할 때 사용
		   				java.lang, javax.http.servlet
		   				============================= 생략 가능
		   			 => 사용형식 => 2
		   			 <%@ page import="java.util.*,java.io.*"%>
		   			 <%@ page import="java.util.*">
		   			 <%@ page import="java.io.*"%>
		   3. buffer => html을 저장하는 메모리 공간
		   			 => 8kb
		   			 => html이 출력을 할 때 용량이 부족시에는 증가
		   			 	buffer="16kb"
		   4. errorPage
		   
		taglib : 태그로 자바 기본 문법을 제공 => jstl/el
		include : JSP안에 다른 JSP를 첨부
		<%@ 지시자명 속성="값"
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