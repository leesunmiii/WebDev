<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  조건이 있을때나 for문이 있을 떄 : <taglib 사용 -->
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
         <table class="table">
            <tr>
               <td width="35%" align="center" rowspan="9">
                  <img src="${vo.poster }" id="image">
               </td>
               <td width="65%" align="center">
                  <span id="title">
                  ${vo.title }
                  </span>
               </td>
            </tr>
            <%-- <tr>
               <td width="65%">
                  <span id="sub">${vo.title }</span>
               </td>
            </tr> --%>
            <tr>
               <td width="65%">
                  <!--  <span id="percent"></span>&nbsp;-->
                  <span id="price">${vo.original_price }</span>
                  <p>
                     <del id="psub">398,000원</del>
                  </p>
               </td>
            </tr>
            <tr>
               <td width="65%">
                  <span id="label">첫구매할인가</span>
                  <span id="price2">${vo.selling_price }</span>
               </td>
            </tr>
            <tr>
               <td width="65%">
                  <span id="star">★★★★★</span>&nbsp;
                  <span id="bold">4.5</span>
                  <span id="count">(299)</span>
               </td>
            </tr>
            <tr>
               <td width="65%">
                  <img src="https://recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png">&nbsp;&nbsp;&nbsp;&nbsp;
                  <span id="delivery">${vo.delivery_price }</span>
               </td>
            </tr>
            <tr>
               <td width="65%">
                  <select id="sel">
                     <option>옵션 선택</option>
                  </select>
               </td>
            </tr>
            <tr>
               <td width="65%">
                  <input type="button" value="장바구니" id="cart">
                  <input type="button" value="바로구매" id="buy">
                  <input type="button" value="목록" id="cart"
                  onclick="javascript:history.back()"
                  >
               </td>
            </tr>
         </table>
      </div>
</body>
</html>