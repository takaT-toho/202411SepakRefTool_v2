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
<link href="/demo202411/css/tossForServeAndSide-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>

<div class="step-container">
	<div class="progress-bar">
		<div class="progress-line">
			<div class="line"></div>
			<div class="line"></div>
		</div>
		<div class="steps">
			<div class="step">
				<div class="step-circle active">1</div>
				<span class="step-text active">サービス/サイド決め</span>
			</div>
			<div class="step">
				<div class="step-circle">2</div>
				<span class="step-text">2分間練習</span>
			</div>
			<div class="step">
				<div class="step-circle">3</div>
				<span class="step-text">チーム・選手紹介</span>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<div class="explain-card-header">
		<h2>サービス/サイド決め手順</h2>
	</div>
	<div class="explain-steps">
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">主審の行動</span>
				<span class="explain-step-number">Step 1</span>
			</div>
			<p class="explain-step-description">両チームのキャプテンを呼び集めてください。</p>
			<div class="explain-quote">
				<p>"両チームキャプテン！"</p>
			</div>
		</div>
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">主審の行動</span>
				<span class="explain-step-number">Step 2</span>
			</div>
			<p class="explain-step-description">コイントスを行います。</p>
			<p class="explain-step-description">トスに勝ったチームが、「サービスの選択権」または「コートサイドの選択権」のどちらかを選べます。</p>
			<p class="explain-step-description">トスに負けたチームは、残りの選択権を有します。</p>
		</div>
	</div>

	<div id="coin-container">
	    <svg id="coin" width="100" height="100" viewBox="0 0 100 100">
	      <circle cx="50" cy="50" r="40" fill="#BF9F00" />
	      <circle cx="50" cy="50" r="35" fill="#ffd700" />
	      <text x="50" y="50" fill="#4c3f00" text-anchor="middle" alignment-baseline="middle" font-size="24">表</text>
	    </svg>
	</div>
	
	<div class="tossButton" id="tossButton">トス</div>
	<div class="toss-regu-container">
		<table>
			<tr>
				<th></th>
				<th><c:out value="${sessionScope.reguA.name}" /></th>
				<th><c:out value="${sessionScope.reguB.name}" /></th>
			</tr>
			<tr>
				<td>トスの<br>勝ちチーム</td>
				<td id="teamA" onclick="selectWinner('A')"></td>
			<td id="teamB" onclick="selectWinner('B')"></td>
			</tr>
		</table>
	</div>

	<div class="explain-steps">
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">主審の行動</span>
				<span class="explain-step-number">Step 3</span>
			</div>
			<p class="explain-step-description">各チームの選択に聞いたうえで、サービスとコートサイドの設定を行いましょう。</p>
		</div>
	</div>

	<div class="regu-container">
	  <table>
	    <thead>
	      <tr>
	        <th><div id="labelLeft"><c:out value="${sessionScope.reguA.name}" /></div></th>
	        <th></th>
	        <th><div id="labelRight"><c:out value="${sessionScope.reguB.name}" /></div></th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td><div class="serveCell active" id="serveLeft">先手</div></td>
	        <td><div class="buttons serveButton" id="serveButton">サービス<br>⇔</div></td>
	        <td><div class="serveCell" id="serveRight">後手</div></td>
	      </tr>
	      <tr>
	        <td><div class="court">左</div></td>
	        <td><div class="buttons courtButton" id="courtButton">コート<br>⇔</div></td>
	        <td><div class="court">右</div></td>
	      </tr>
	    </tbody>
	  </table>
	</div>
	
	<div class="explain-steps">
		<div class="explain-step">
			<p class="explain-step-description">コイントスに勝ったチームが、最初に2分間のウォーミングアップを行います。2分練へ進みましょう。</p>
		</div>
	</div>

	<form action="judgeFC" method="post">
		<input type="hidden" id="isAreguTossWin" name="isAreguTossWin" value="1">
		<input type="hidden" id="serve" name="serve" value="1">
		<input type="hidden" id="court" name="court" value="1">
		<button type="submit" name="buttonId" value="p0004">2分練へ</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/demo202411/js/coinToss.js"></script>
<script type="text/javascript" src="/demo202411/js/updateServeCourt.js"></script>
</body>
</html>