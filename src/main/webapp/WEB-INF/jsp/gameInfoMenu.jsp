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
<link href="/demo202411/css/gameInfoMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>試合情報</h2>
	<div class="match-header">
	  <div class="round"><c:out value="${ sessionScope.game.round }" /></div>
	  <div class="match-info">
	    <span><c:out value="${ sessionScope.game.time }" /> -</span>
	    <span><c:out value="${ sessionScope.court }" />コート</span>
	  </div>
	  <div class="match-info">
	    <span>試合番号: <c:out value="${ sessionScope.game.gameId }" /></span>
	    <span>試合名: <c:out value="${ sessionScope.game.name }" /></span>
	  </div>
	</div>
	
	<div class="teams">
	  <div class="team team-a"><c:out value="${ sessionScope.reguA.name }" /></div>
	  <div class="vs">VS</div>
	  <div class="team team-b"><c:out value="${ sessionScope.reguB.name }" /></div>
	</div>
	
	<div class="referees">
		<div>審判：</div>
		<div><c:out value="${ sessionScope.mainRef.name }" /></div>
		<div>&</div>
		<div><c:out value="${ sessionScope.subRef.name }" /></div>
	</div>
	
	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0006">戻る</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>