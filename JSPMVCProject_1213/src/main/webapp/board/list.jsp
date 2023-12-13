<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
   .container{
      margin-top: 50px;
   }
   .row{
      margin: 0px auto;
      width: 800px;
   }
</style>
</head>
<body>
   <div class="row">
		<h1 class="text-center">전체상품</h1>
			<c:forEach var="vo" items="${list}">
				<div class="col-md-3">
	    			<div class="thumbnail">
	      				<a href="detail.do?no=${vo.gno }">
	        			<img src="${vo.poster }" title="${vo.title }" style="width:100%">
		        			<div class="caption">
		         			<p>${vo.original_price }원</p>
		         			</div>
	      				</a>
	    			</div>
	  			</div>
	 		 </c:forEach>
	</div>
	
	<div style="height: 10px"></div>
	<div class="row">
		<div class="text-center">
			<ul class="pagination">
			<%--
				<!ENTITY lt "<">
				<!ENTITY gt ">">
				=> &lt;
			 --%>
			  <c:if test="${startPage>1 }">
			  <li><a href="list.do?page=${startPage-1}">&lt;</a></li>
			  </c:if>
			  <c:forEach var="i" begin="${startPage  }" end="${endPage }">
			  <li ${curpage==i?"class=active":"" }><a href="list.do?page=${i}">${i }</a></li>
			  <%--											=============================== 페이지 바뀌는 코드	--%>
			  </c:forEach>
			  <c:if test="">
			  <li><a href="list.do?page=${endPage-1}">&gt;</a></li>
			  </c:if>
			</ul>
		</div>
	</div>
</body>
</html>