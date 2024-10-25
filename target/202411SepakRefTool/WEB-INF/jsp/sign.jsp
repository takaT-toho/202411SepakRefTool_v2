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
<link href="/202411SepakRefTool/css/sign-jsp.css" rel="stylesheet" />

</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<div class="match-header">
	  <div class="round"><c:out value="${sessionScope.game.round}"/></div>
	  <div class="match-info">
	    <span><c:out value="${sessionScope.game.time}"/> -</span>
	    <span><c:out value="${sessionScope.court}"/>コート</span>
	  </div>
	  <div class="match-info">
	    <span>試合番号: <c:out value="${sessionScope.game.gameId}"/></span>
	    <span>試合名: <c:out value="${sessionScope.game.name}"/></span>
	  </div>
	</div>
	
	<div class="teams">
	  <div class="team team-a"><c:out value="${sessionScope.reguA.abbreviation}"/></div>
	  <div class="vs">VS</div>
	  <div class="team team-b"><c:out value="${sessionScope.reguB.abbreviation}"/></div>
	</div>
	
	<div class="referees">
		<div>【主審】</div>
		<div><c:out value="${sessionScope.mainRef.abbreviation}"/></div>
		<c:if test="${sessionScope.subRef != null}">
			<div>【副審】</div>
			<div><c:out value="${sessionScope.subRef.abbreviation}"/></div>
		</c:if>
	</div>

	<div class="explain-steps">
		<div class="explain-step">
			<p class="explain-step-description">上記の試合で合っていますか？</p>
			<p class="explain-step-description">確認したら、ご自身のお名前を記入の上、次へお進みください。</p>
		</div>
	</div>
	
	<form action="/202411SepakRefTool/judgeFC" method="post" id="checkForm">
		<input type="text" name="sign" id="sign" placeholder="署名" pattern=".{0,20}">
		<button type="submit" name="buttonId" value="p0003">次へ</button>
	</form>
	<div class="errorMsg" id="errorMsg"></div>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/202411SepakRefTool/js/signInputCheck.js"></script>
</body>
</html>