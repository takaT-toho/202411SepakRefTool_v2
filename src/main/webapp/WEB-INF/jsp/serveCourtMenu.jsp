<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>審判支援システム</title>
<link href="/202411SepakRefTool/css/header-non-menu.css" rel="stylesheet" />
<link href="/202411SepakRefTool/css/form.css" rel="stylesheet" />
<link href="/202411SepakRefTool/css/serveCourtMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>サービス/コートサイド選択</h2>
	
	<div class="regu-container">
	  <table>
	    <thead>
	      <tr>
			<c:if test="${sessionScope.gameConfig.isAreguLeft}">
				<th><div id="labelLeft"><c:out value="${ sessionScope.reguA.abbreviation }" /></div></th>
				<th></th>
				<th><div id="labelRight"><c:out value="${ sessionScope.reguB.abbreviation }" /></div></th>
			</c:if>
			<c:if test="${!sessionScope.gameConfig.isAreguLeft}">
				<th><div id="labelLeft"><c:out value="${ sessionScope.reguB.abbreviation }" /></div></th>
				<th></th>
				<th><div id="labelRight"><c:out value="${ sessionScope.reguA.abbreviation }" /></div></th>
			</c:if>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
			<c:if test="${sessionScope.gameConfig.isAreguLeft == sessionScope.gameConfig.isAreguFirstServe}">
				<td><div class="serveCell active" id="serveLeft">先手</div></td>
				<td><div class="buttons serveButton" id="serveButton">サービス<br>⇔</div></td>
				<td><div class="serveCell" id="serveRight">後手</div></td>
			</c:if>
			<c:if test="${sessionScope.gameConfig.isAreguLeft != sessionScope.gameConfig.isAreguFirstServe}">
				<td><div class="serveCell" id="serveLeft">先手</div></td>
				<td><div class="buttons serveButton" id="serveButton">サービス<br>⇔</div></td>
				<td><div class="serveCell active" id="serveRight">後手</div></td>
			</c:if>
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
		<input type="hidden" id="serve" name="serve" 
		value=<c:out value="${ sessionScope.gameConfig.isAreguFirstServe ? 1 : 0 }" />>
		<input type="hidden" id="court" name="court" value=<c:out value="${ sessionScope.gameConfig.isAreguLeft ? 1 : 0 }" />>
		<button type="submit" name="buttonId" value="p0006">キャンセル</button>
		<button type="submit" name="buttonId" value="p0304">変更する</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/202411SepakRefTool/js/updateServeCourt.js"></script>
</body>
</html>