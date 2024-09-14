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
	<div class="step active">
		<div class="step-text">サービス/<br>サイド決め</div>
	</div>
	<div class="arrow active">&gt;</div>
	<div class="step">
		<div class="step-text">2分間練習</div>
	</div>
	<div  class="arrow">&gt;</div>
	<div class="step">
		<div class="step-text">チーム・<br>選手紹介</div>
	</div>
</div>

<div class="container">
	<h3>1. 主審が、両チームのキャプテンを呼び集めてください。</h3>
	<p>「両チーム キャプテン！」</p>

	<h3>2. 主審が、コイントスを行います。</h3>
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
	
	<p>トスに勝ったチームが、"サービスの選択権"か"コートサイドの選択権"を選びます。</p>
	<p>トスに負けたチームは、残りの選択権を有します。</p>

	<h3>3. 主審は、各チームの選択に従って、サービスとコートサイドの設定を行いましょう。</h3>
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
	
	<p>コイントスに勝ったチームが、最初に2分間のウォーミングアップを行います。</p>

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