<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>審判支援システム</title>
<link href="/demo202411/css/header.css" rel="stylesheet" />
<link href="/demo202411/css/main-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
	<div id="isAreguLeft" hidden><c:out value="${ sessionScope.gameConfig.isAreguLeft }" /></div>
	<div id="isAreguFirstServe" hidden><c:out value="${ sessionScope.gameConfig.isAreguFirstServe }" /></div>
	<div id="is3setCourtChanged" hidden><c:out value="${ sessionScope.gameDetail.is3setCourtChanged }" /></div>
	<div id="sumPoints" hidden><c:out value="${ sessionScope.gameDetail.sumPoints }" /></div>
	<div id="reguAName" hidden><c:out value="${ sessionScope.reguA.name }" /></div>
	<div id="reguBName" hidden><c:out value="${ sessionScope.reguB.name }" /></div>
	<div id="setNumGotByA" hidden><c:out value="${ sessionScope.game.setNumGotByA }" /></div>
	<div id="setNumGotByB" hidden><c:out value="${ sessionScope.game.setNumGotByB }" /></div>
	<div id="isFin1Set" hidden><c:out value="${ sessionScope.game.isFin1Set }" /></div>
	<div id="isFin2Set" hidden><c:out value="${ sessionScope.game.isFin2Set }" /></div>
	<div id="isFin3Set" hidden><c:out value="${ sessionScope.game.isFin3Set }" /></div>

	<span id="LANGUAGE" hidden><c:out value="${ sessionScope.gameConfig.language }" /></span>
	<span id="MAX_SCORE_1SET" hidden><c:out value="${ sessionScope.gameConfig.maxScore1set }" /></span>
	<span id="MAX_SCORE_2SET" hidden><c:out value="${ sessionScope.gameConfig.maxScore2set }" /></span>
	<span id="MAX_SCORE_3SET" hidden><c:out value="${ sessionScope.gameConfig.maxScore3set }" /></span>
	<span id="DEUCE_START_SCORE_1SET" hidden><c:out value="${ sessionScope.gameConfig.deuceStartScore1set }" /></span>
	<span id="DEUCE_START_SCORE_2SET" hidden><c:out value="${ sessionScope.gameConfig.deuceStartScore2set }" /></span>
	<span id="DEUCE_START_SCORE_3SET" hidden><c:out value="${ sessionScope.gameConfig.deuceStartScore3set }" /></span>
	<span id="DEUCE_DIFFERENCE_1SET" hidden><c:out value="${ sessionScope.gameConfig.deuceDifference1set }" /></span>
	<span id="DEUCE_DIFFERENCE_2SET" hidden><c:out value="${ sessionScope.gameConfig.deuceDifference2set }" /></span>
	<span id="DEUCE_DIFFERENCE_3SET" hidden><c:out value="${ sessionScope.gameConfig.deuceDifference3set }" /></span>
	<span id="COURT_CHANGE_SCORE" hidden><c:out value="${ sessionScope.gameConfig.courtChangeScore }" /></span>
	<span id="MAX_SET" hidden><c:out value="${ sessionScope.gameConfig.maxSet }" /></span>
	
	<div id="GAME_RULE" hidden><c:out value="${ sessionScope.gameConfig.gameRule }" /></div>

	<div class="scoreboard">
	  <div class="team team-a">
	    <div class="team-name" id="teamLeft"><span class="reguName">
			<c:choose>
				<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
					<c:out value="${ sessionScope.reguA.name }" />
				</c:when>
				<c:otherwise>
					<c:out value="${ sessionScope.reguB.name }" />
				</c:otherwise>
			</c:choose>
		</span></div>
	    <div class="serveGages">
	    	<div class="serveGage" id="serveGageLeft1"></div>
	    	<div class="serveGage" id="serveGageLeft2"></div>
	    	<div class="serveGage" id="serveGageLeft3"></div>
	    </div>
	    <div class="score" id="scoreLeft">
			<c:choose>
				<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
					<c:out value="${ sessionScope.gameDetail.getPointsA()[sessionScope.game.setNow - 1] }" />
				</c:when>
				<c:otherwise>
					<c:out value="${ sessionScope.gameDetail.getPointsB()[sessionScope.game.setNow - 1] }" />
				</c:otherwise>
			</c:choose>
		</div>
	    <div class="sets">
	      <div class="set">
	        <div class="set-number" id="setNumber1setLeft">
				<c:choose>
					<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
						<c:out value="${ sessionScope.gameDetail.getPointsA()[0] }" />
					</c:when>
					<c:otherwise>
						<c:out value="${ sessionScope.gameDetail.getPointsB()[0] }" />
					</c:otherwise>
				</c:choose>
			</div>
	        <div class="set-label">1SET</div>
	      </div>
	      <div class="set">
	        <div class="set-number" id="setNumber2setLeft">
				<c:choose>
					<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
						<c:out value="${ sessionScope.gameDetail.getPointsA()[1] }" />
					</c:when>
					<c:otherwise>
						<c:out value="${ sessionScope.gameDetail.getPointsB()[1] }" />
					</c:otherwise>
				</c:choose>
			</div>
	        <div class="set-label">2SET</div>
	      </div>
	      <div class="set">
	        <div class="set-number" id="setNumber3setLeft">
				<c:choose>
					<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
						<c:out value="${ sessionScope.gameDetail.getPointsA()[2] }" />
					</c:when>
					<c:otherwise>
						<c:out value="${ sessionScope.gameDetail.getPointsB()[2] }" />
					</c:otherwise>
				</c:choose>
			</div>
	        <div class="set-label">3SET</div>
	      </div>
	    </div>
	  </div>
	
	  <div class="team team-b">
	    <div class="team-name" id="teamRight"><span class="reguName">
			<c:choose>
				<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
					<c:out value="${ sessionScope.reguB.name }" />
				</c:when>
				<c:otherwise>
					<c:out value="${ sessionScope.reguA.name }" />
				</c:otherwise>
			</c:choose>
		</span></div>
	    <div class="serveGages">
	    	<div class="serveGage" id="serveGageRight1"></div>
	    	<div class="serveGage" id="serveGageRight2"></div>
	    	<div class="serveGage" id="serveGageRight3"></div>
	    </div>
	    <div class="score" id="scoreRight">
			<c:choose>
				<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
					<c:out value="${ sessionScope.gameDetail.getPointsB()[sessionScope.game.setNow - 1] }" />
				</c:when>
				<c:otherwise>
					<c:out value="${ sessionScope.gameDetail.getPointsA()[sessionScope.game.setNow - 1] }" />
				</c:otherwise>
			</c:choose>
		</div>
	    <div class="sets">
	      <div class="set">
	        <div class="set-number" id="setNumber1setRight">
				<c:choose>
					<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
						<c:out value="${ sessionScope.gameDetail.getPointsB()[0] }" />
					</c:when>
					<c:otherwise>
						<c:out value="${ sessionScope.gameDetail.getPointsA()[0] }" />
					</c:otherwise>
				</c:choose>
			</div>
	        <div class="set-label">1SET</div>
	      </div>
	      <div class="set">
	        <div class="set-number" id="setNumber2setRight">
				<c:choose>
					<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
						<c:out value="${ sessionScope.gameDetail.getPointsB()[1] }" />
					</c:when>
					<c:otherwise>
						<c:out value="${ sessionScope.gameDetail.getPointsA()[1] }" />
					</c:otherwise>
				</c:choose>
			</div>
	        <div class="set-label">2SET</div>
	      </div>
	      <div class="set">
	        <div class="set-number" id="setNumber3setRight">
				<c:choose>
					<c:when test="${ (sessionScope.gameConfig.isAreguLeft == (sessionScope.game.setNow % 2 == 1)) == !sessionScope.gameDetail.is3setCourtChanged }">
						<c:out value="${ sessionScope.gameDetail.getPointsB()[2] }" />
					</c:when>
					<c:otherwise>
						<c:out value="${ sessionScope.gameDetail.getPointsA()[2] }" />
					</c:otherwise>
				</c:choose>
			</div>
	        <div class="set-label">3SET</div>
	      </div>
	    </div>
	  </div>
	</div>
	  
	<div class="addPointContianer">
		<div class="addPointButton" id="addLeftRegu">+1</div>
		<div class="addPointButton" id="addRightRegu">+1</div>
	</div>
	
	
	<div class="callMsgContainer">
		<div class="callMsgLine"></div>
		<div class="callMsgArea">
			<div id="callMsg">メッセージ</div>
			<!-- <div id="callMsgEng">メッセージ</div> -->
		</div>
		<div class="callMsgLine"></div>
	</div>
	
	<div class="middleButtonContainer">
		<button class="undo-button" aria-label="元に戻す" id="backGame">
			<svg class="undo-icon" viewBox="0 0 24 24">
				<path d="M1 4v6h6"></path>
				<path d="M3.51 15a9 9 0 1 0 2.13-9.36L1 10"></path>
			</svg>
		</button>
		<form action="judgeFC" method="post" id="main-submit-form" class="formContainer">
			<input type="text" name="setNow" id="setNow" value="<c:out value='${sessionScope.game.setNow}'/>" hidden>
			<input type="text" name="isAreguSetWin" id="isAreguSetWin" value="" hidden>
			<button type="submit" name="buttonId" id="SetEndButton" value="p0201">1セット目の結果を送る</button>
		</form>
	</div>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/main.js"></script>
<script type="text/javascript" src="/demo202411/js/mainAsync.js"></script>
<script type="text/javascript" src="/demo202411/js/header.js"></script>
</body>
</html>