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
<link href="/202411SepakRefTool/css/gameSettingProgress.css" rel="stylesheet" />
<link href="/202411SepakRefTool/css/gameReady-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>	

<div class="step-container">
	<div class="progress-bar">
		<div class="progress-line">
			<div class="line active"></div>
			<div class="line active"></div>
		</div>
		<div class="steps">
			<div class="step">
				<div class="step-circle completed">1</div>
				<span class="step-text">サービス/サイド決め</span>
			</div>
			<div class="step">
				<div class="step-circle completed">2</div>
				<span class="step-text">2分間練習</span>
			</div>
			<div class="step">
				<div class="step-circle active">3</div>
				<span class="step-text active">チーム・選手紹介</span>
			</div>
		</div>
	</div>
</div>

<div class="container">
    
	<div class="explain-card-header">
		<h2>チーム・選手紹介手順</h2>
	</div>
	<div class="explain-steps">
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">主審の行動</span>
				<span class="explain-step-number">Step 1</span>
			</div>
			<p class="explain-step-description">両チームの選手を各自陣のコートサイドのエンドラインに整列させます。</p>
			<div class="explain-quote">
				<p>例: "試合を開始するので、エンドラインに整列してください！"</p>
			</div>
		</div>
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">主審の行動</span>
				<span class="explain-step-number">Step 2</span>
			</div>
			<p class="explain-step-description">各チームの名前と選手名を読み上げて紹介します。</p>
			<div class="explain-quote">
				<p>"只今より、第<c:out value="${ sessionScope.court }" />コート、第<c:out value="${ sessionScope.game.round }" />試合を始めます。"</p>
			</div>            
			<div class="explain-quote">
                <c:if test="${ sessionScope.gameConfig.isAreguLeft == true }">
                    <p>「オンマイライト <c:out value="${ sessionScope.reguB.abbreviation }" />、
                        <c:forEach var="player" items="${ sessionScope.playerListB }">
                            <br><c:out value="${ player.name }" /> 選手
                        </c:forEach>
                        <%--リザーブ --%>」
                    </p>
                    <p>「オンマイレフト <c:out value="${ sessionScope.reguA.abbreviation }" />、
                        <c:forEach var="player" items="${ sessionScope.playerListA }">
                            <br><c:out value="${ player.name }" /> 選手
                        </c:forEach>
                        <%--リザーブ --%>」
                    </p>
                </c:if>
                <c:if test="${ sessionScope.gameConfig.isAreguLeft == false }">
                    <p>「オンマイライト <c:out value="${ sessionScope.reguA.abbreviation }" />、
                        <c:forEach var="player" items="${ sessionScope.playerListA }">
                            <br><c:out value="${ player.name }" /> 選手
                        </c:forEach>
                        <%--リザーブ --%>」
                    </p>
                    <p>「オンマイレフト <c:out value="${ sessionScope.reguB.abbreviation }" />、
                        <c:forEach var="player" items="${ sessionScope.playerListB }">
                            <br><c:out value="${ player.name }" /> 選手
                        </c:forEach>
                        <%--リザーブ --%>」
                    </p>
                </c:if>
			</div>
		</div>
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">主審の行動</span>
				<span class="explain-step-number">Step 3</span>
			</div>
			<p class="explain-step-description">紹介終了後、試合開始の合図を出します。</p>
			<div class="explain-quote">
				<p>"レディー！"</p>
			</div>
		</div>
	</div>

	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0006">試合開始</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>