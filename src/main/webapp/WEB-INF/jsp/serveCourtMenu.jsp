<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>審判支援システム</title>
<link href="/demo202411/css/header-non-menu.css" rel="stylesheet" />
<link href="/demo202411/css/form.css" rel="stylesheet" />
<link href="/demo202411/css/serveCourtMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>サービス/コートサイド選択</h2>
	
	<div class="regu-container">
	  <table>
	    <thead>
	      <tr>
	        <th><div id="labelLeft"><c:out value="${ sessionScope.reguA.name }" /></div></th>
	        <th></th>
	        <th><div id="labelRight"><c:out value="${ sessionScope.reguB.name }" /></div></th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td><div class="serveCell active" id="serveLeft">先手</div></td>
	        <td><div class="buttons serveButton" id="serveButton">サービス<br>⇔</div></td>
	        <td><div class="serveCell" id="serveRight">後手</div></td>
	      </tr>
	      <tr>
	        <td><div class="court">左</div></td>
	        <td><div class="buttons courtButton" id="courtButton">コート<br>⇔</div></td>
	        <td><div class="court">右</div></td>
	      </tr>
	    </tbody>
	  </table>
	</div>
	
	<form action="judgeFC" method="post">
		<input type="hidden" id="isAreguTossWin" name="isAreguTossWin" value="1">
		<input type="hidden" id="serve" name="serve" value="1">
		<input type="hidden" id="court" name="court" value="1">
		<button type="submit" name="buttonId" value="p0006">キャンセル</button>
		<button type="submit" name="buttonId" value="p0006">変更する</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/updateServeCourt.js"></script>
</body>
</html>