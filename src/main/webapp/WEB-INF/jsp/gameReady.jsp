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
<link href="/demo202411/css/gameSettingProgress.css" rel="stylesheet" />
<link href="/demo202411/css/gameReady-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>	

<div class="step-container">
	<div class="step">
		<div class="step-text">サービス/<br>サイド決め</div>
	</div>
	<div class="arrow">&gt;</div>
	<div class="step">
		<div class="step-text">2分間練習</div>
	</div>
	<div  class="arrow">&gt;</div>
	<div class="step active">
		<div class="step-text">チーム・<br>選手紹介</div>
	</div>
</div>

<div class="container">

	<h3>1. 主審は、両チームの選手を各自陣のコートサイドのエンドラインに整列させます。</h3>
	<h3>2. 各チームの名前と選手名を読み上げて紹介します。</h3>
	<p>「只今より、第<c:out value="${ sessionScope.court }" />コート、第<c:out value="${ sessionScope.game.round }" />試合を始めます。」</p>	

    <c:if test="${ sessionScope.gameConfig.isAreguLeft == true }">
		<p>「オンマイライト <c:out value="${ sessionScope.reguB.name }" />、<br>
            <c:forEach var="player" items="${ sessionScope.playerListB }">
                <c:out value="${ player.name }" />選手<br>
            </c:forEach>
            <%--リザーブ --%>」
        </p>
		<p>「オンマイレフト <c:out value="${ sessionScope.reguA.name }" />、<br>
            <c:forEach var="player" items="${ sessionScope.playerListA }">
                <c:out value="${ player.name }" />選手<br>
            </c:forEach>
            <%--リザーブ --%>」
        </p>
    </c:if>
    <c:if test="${ sessionScope.gameConfig.isAreguLeft == false }">
		<p>「オンマイライト <c:out value="${ sessionScope.reguA.name }" />、<br>
            <c:forEach var="player" items="${ sessionScope.playerListA }">
                <c:out value="${ player.name }" />選手<br>
            </c:forEach>
            <%--リザーブ --%>」
        </p>
		<p>「オンマイレフト <c:out value="${ sessionScope.reguB.name }" />、<br>
            <c:forEach var="player" items="${ sessionScope.playerListB }">
                <c:out value="${ player.name }" />選手<br>
            </c:forEach>
            <%--リザーブ --%>」
        </p>
    </c:if>

	<h3>3. 紹介終了後、主審は試合開始の合図を出します。</h3>
	<p>「レディー！」</p>

	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0006">試合開始</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>