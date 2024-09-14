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
	
	
	<div class="callMsgArea">
		<div id="callMsg">メッセージ</div>
		<!-- <div id="callMsgEng">メッセージ</div> -->
	</div>
	
	<div class="middleButtonContainer">
		<div id="backGame">←</div>
		<form action="judgeFC" method="post" id="main-submit-form">
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