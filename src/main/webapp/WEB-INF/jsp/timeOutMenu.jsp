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
<link href="/demo202411/css/timeOutMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>タイムアウト</h2>
	
	
	<h3>1. タイムアウトを申請したレグを選択してください。</h3>	
	<div class="reguRequestedContainer">
	  <table>
	    <thead>
	      <tr>
	        <th><div class="reguRequested active" onclick="onReguClick(this, 'A')"><c:out value="${ sessionScope.reguA.name }" /></div></th>
	        <th><div class="reguRequested" onclick="onReguClick(this, 'B')"><c:out value="${ sessionScope.reguB.name }" /></div></th>
	      </tr>
	    </thead>
	  </table>
	</div>
		
	<h3>2. タイムアウトの種類を選択してください。</h3>	
	<div class="timerTypeContainer">
	  <table>
	    <thead>
	      <tr>
	        <th><div class="timerType active" onclick="onTimeoutTypeClick(this, true)">ノーマルタイムアウト</div></th>
	        <th><div class="timerType" onclick="onTimeoutTypeClick(this, false)">インジュリータイムアウト</div></th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td><div class="remainedNumber" id="remainedNumberOfNormalTimeout">
				残り <c:out value="${ sessionScope.gameDetail.normalTimeoutRemainA }" />回
			</div></td>
	        <td><div class="remainedNumber" id="remainedNumberOfInjuryTimeout">
				残り <c:out value="${ sessionScope.gameDetail.technicalTimeoutRemainA }" />回
			</div></td>
	      </tr>
	    </tbody>
	  </table>
	</div>
	
	<h3>3. 再生ボタンを押すとタイマーが開始します。</h3>	
	<div class="timer">01:00</div>
	<div class="buttons">
		<svg class="button start" viewBox="0 0 24 24">
			<path d="M8 6v12l9-6z" fill="lightgreen" stroke="none" stroke-width="1" stroke-linejoin="round"/>
			<path d="M8 6v12l9-6z" fill="none" stroke="lightgreen" stroke-width="2" stroke-linejoin="round"/>
		</svg>
		<svg class="button pause hidden" viewBox="0 0 24 24">
			<path d="M6 5h4v14H6V5zm8 0h4v14h-4V5z" fill="#f0f0f0" stroke="none" stroke-width="1" stroke-linejoin="round"/>
			<rect x="6" y="5" width="4" height="14" rx="1" ry="1" fill="#f0f0f0"/>
			<rect x="14" y="5" width="4" height="14" rx="1" ry="1" fill="#f0f0f0"/>
		</svg>
		<svg class="button stop hidden" viewBox="0 0 24 24">
			<rect x="5" y="5" width="14" height="14" rx="2" ry="2" fill="#DC143C"/>
		</svg>
	</div>
	
	<div class="errorMsg" id="errorMsg"></div>
	
	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0006">戻る</button>
	</form>
</div>

<span id="gameId" style="display: none;"><c:out value="${ sessionScope.game.gameId }" /></span>
<span id="setNow" style="display: none;"><c:out value="${ sessionScope.game.setNow }" /></span>
<span id="normalTimeoutRemainA" style="display: none;"><c:out value="${ sessionScope.gameDetail.normalTimeoutRemainA }" /></span>
<span id="technicalTimeoutRemainA" style="display: none;"><c:out value="${ sessionScope.gameDetail.technicalTimeoutRemainA }" /></span>
<span id="normalTimeoutRemainB" style="display: none;"><c:out value="${ sessionScope.gameDetail.normalTimeoutRemainB }" /></span>
<span id="technicalTimeoutRemainB" style="display: none;"><c:out value="${ sessionScope.gameDetail.technicalTimeoutRemainB }" /></span>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/optionalTimer.js"></script>
<script type="text/javascript" src="/demo202411/js/optionalTimerAsync.js"></script>
</body>
</html>