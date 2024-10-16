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
<link href="/demo202411/css/rps-jsp.css" rel="stylesheet" />

</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<div class="regus-container">
		<table>
			<tr>
				<th></th>
				<th><c:out value="${sessionScope.reguA.name}" /></th>
				<th><c:out value="${sessionScope.reguB.name}" /></th>
			</tr>
			<tr>
				<td>じゃんけんの<br>勝者レグ</td>
				<td id="teamA" onclick="selectWinner('A')"></td>
			    <td id="teamB" onclick="selectWinner('B')"></td>
			</tr>
		</table>
	</div>
	
	<form action="/demo202411/judgeFC" method="post" id="checkForm">
        <input type="text" name="isAreguWin" id="isAreguWin" value="" hidden></span>
		<button type="submit" name="buttonId" value="p0205">試合を終了する</button>
	</form>
	<div class="errorMsg" id="errorMsg"></div>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/rps-jsp.js"></script>
</body>
</html>