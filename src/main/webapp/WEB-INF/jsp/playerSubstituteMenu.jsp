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
<link href="/demo202411/css/playerSubstituteMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>選手交代</h2>
	
	<h3>1. 選手交代を実施するレグを選択してください。</h3>	
	<div class="reguRequestedContainer">
	  <table>
	    <thead>
	      <tr>
	        <th><div class="reguRequested active" id="reguRequestedA" onclick="onReguClick(this, 'A')"><c:out value="${ sessionScope.reguA.name }" /></div></th>
	        <th><div class="reguRequested" id="reguRequestedB" onclick="onReguClick(this, 'B')"><c:out value="${ sessionScope.reguB.name }" /></div></th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td><div class="remainedNumber" id="remainedNumberOfSubstitutionByA">
				残り <span id="playerSubstitutionRemainA"><c:out value="${ sessionScope.gameDetail.playerSubstitutionRemainA }" /></span>回
			</div></td>
	        <td><div class="remainedNumber" id="remainedNumberOfSubstitutionByB">
				残り <span id="playerSubstitutionRemainB"><c:out value="${ sessionScope.gameDetail.playerSubstitutionRemainB }" /></span>回
			</div></td>
	      </tr>
	    </tbody>
	  </table>
	</div>
	
	<h3>2. 交代する2選手を選択してください。</h3>
	<div class="playersContainer">
	  <table>
	    <thead>
	      <tr>
	        <th><div>コート内</div></th>
	        <th><div></div></th>
	        <th><div>控え</div></th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr id="tr1st">
	        <td><div class="inCourt" id="inCourt1st">
				<c:out value="${ sessionScope.playerListA[0].name }" />
			</div></td>
	        <td><div class="status" id="status1st">・</div></td>
	        <td><div class="substitution" id="substitution1st"></div></td>
	      </tr>
	      <tr id="tr2nd">
	        <td><div class="inCourt" id="inCourt2nd">
				<c:out value="${ sessionScope.playerListA[1].name }" />
			</div></td>
	        <td><div class="status" id="status2nd">・</div></td>
	        <td><div class="substitution" id="substitution2nd"></div></td>
	      </tr>
	      <tr id="tr3rd">
	        <td><div class="inCourt" id="inCourt3rd">
				<c:out value="${ sessionScope.playerListA[2].name }" />
			</div></td>
	        <td><div class="status" id="status3rd">・</div></td>
	        <td><div class="substitution" id="substitution3rd"></div></td>
	      </tr>
	      <tr id="tr4th">
	        <td><div class="inCourt" id="inCourt4th"></div></td>
	        <td><div class="status" id="status4th">・</div></td>
	        <td><div class="substitution" id="substitution4th">
				<c:out value="${ sessionScope.playerListA[3].name }" />
			</div></td>
	      </tr>
	      <tr id="tr5th">
	        <td><div class="inCourt" id="inCourt5th"></div></td>
	        <td><div class="status" id="status5th">・</div></td>
	        <td><div class="substitution" id="substitution5th">
				<c:out value="${ sessionScope.playerListA[4].name }" />
			</div></td>
	      </tr>
	    </tbody>
	  </table>
	</div>
	
	<p class="errorMsg"><c:out value = "${ requestScope.errorMsg }" /></p>
	<c:forEach var="msg" items="${ requestScope.errorMsgList }">
		<p class="errorMsg"><c:out value="${ msg }" /></p>
	</c:forEach>
	<div class="errorMsg" id="errorMsg"></div>
	
	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0006">戻る</button>
	</form>
	
	<div class="executeButton" id="executeButton" onclick="onExecuteButtonClick()">交代する</div>
</div>

<span id="player1A" style="display: none;"><c:out value="${ sessionScope.playerListA[0].name }" /></span>
<span id="player2A" style="display: none;"><c:out value="${ sessionScope.playerListA[1].name }" /></span>
<span id="player3A" style="display: none;"><c:out value="${ sessionScope.playerListA[2].name }" /></span>
<span id="player4A" style="display: none;"><c:out value="${ sessionScope.playerListA[3].name }" /></span>
<span id="player5A" style="display: none;"><c:out value="${ sessionScope.playerListA[4].name }" /></span>

<span id="player1B" style="display: none;"><c:out value="${ sessionScope.playerListB[0].name }" /></span>
<span id="player2B" style="display: none;"><c:out value="${ sessionScope.playerListB[1].name }" /></span>
<span id="player3B" style="display: none;"><c:out value="${ sessionScope.playerListB[2].name }" /></span>
<span id="player4B" style="display: none;"><c:out value="${ sessionScope.playerListB[3].name }" /></span>
<span id="player5B" style="display: none;"><c:out value="${ sessionScope.playerListB[4].name }" /></span>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/selectPlayerSubstituteRegu.js"></script>
<script type="text/javascript" src="/demo202411/js/selectPlayerSubstituteReguAsync.js"></script>

</body>
</html>