<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>審判支援システム</title>
<link href="/demo202411/css/header-non-menu.css" rel="stylesheet" />
<link href="/demo202411/css/form.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<form action="judgeFC" method="post" id="checkForm">
		<input type="text" name="gameId" id="gameId" placeholder="試合番号">
		<input type="text" name="pass" id="pass" placeholder="パスワード">
		<button type="submit" name="buttonId" value="p0001">次へ</button>
	</form>
	<p class="errorMsg"><c:out value = "${ requestScope.errorMsg }" /></p>
	<c:forEach var="msg" items="${ requestScope.errorMsgList }">
		<p class="errorMsg"><c:out value="${ msg }" /></p>
	</c:forEach>
	<div class="errorMsg" id="errorMsg"></div>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/gameInfoInputCheck.js"></script>
</body>
</html>