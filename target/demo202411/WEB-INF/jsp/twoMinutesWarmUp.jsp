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
<link href="/demo202411/css/twoMinutesWarmUp-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>	

<div class="step-container">
	<div class="step">
		<div class="step-text">サービス/<br>サイド決め</div>
	</div>
	<div class="arrow">&gt;</div>
	<div class="step active">
		<div class="step-text">2分間練習</div>
	</div>
	<div  class="arrow active">&gt;</div>
	<div class="step">
		<div class="step-text">チーム・<br>選手紹介</div>
	</div>
</div>

<div class="container">
	<h3>1. 主審は、コイントスの勝ちチームから2分間練習開始を合図してください。</h3>
	<p>順番は、「<c:out value="${ sessionScope.tossWinner }" />」→「<c:out value="${ sessionScope.tossLoser }" />」の順です。</p>
	<p>「それでは、2分練を始めてください！」</p>

	<div class="timer">02:00</div>
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

	<p>・タイマーが1分になったら、「残り1分です！」</p>
	<p>・タイマーが終了したら、「終了です！」</p>
	<p>両チームの2分間練習が終了したら、チーム・選手紹介です。</p>

	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0005">チーム・選手紹介へ</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/normalTimer.js"></script>
</body>
</html>