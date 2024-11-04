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
<link href="/202411SepakRefTool/css/tossForServeAndSide-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>

<div class="step-container">
	<div class="progress-bar">
		<div class="line active"></div>
		<div class="step">
			<div class="step-circle active">1</div>
			<span class="step-text step-text-1 active">サービス/サイド決め</span>
		</div>
		<div class="line"></div>
		<div class="step">
			<div class="step-circle">2</div>
			<span class="step-text step-text-2">チーム・選手紹介</span>
		</div>
		<div class="line"></div>
	</div>
</div>

<div class="container">
	<div class="explain-card-header">
		<h2>サービス/サイド決め手順</h2>
	</div>
	<div class="explain-steps">
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">キャプテン集合</span>
				<span class="explain-step-number">Step 1</span>
			</div>
			<p class="explain-step-description">両チームのキャプテンを呼び集めてください。</p>
			<div class="explain-quote">
				<p>"両チームキャプテン！"</p>
			</div>
		</div>
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">コイントス実施</span>
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
		<div class="regu-buttons">
			<div id="reguA" class="regu-button" role="button" tabindex="0" onclick="selectWinner('A')">
			<span class="regu-label"><c:out value="${sessionScope.reguA.abbreviation}" /></span>
			<span class="result-label" id="resultA"></span>
			</div>
			<div id="reguB" class="regu-button" role="button" tabindex="0" onclick="selectWinner('B')">
			<span class="regu-label"><c:out value="${sessionScope.reguB.abbreviation}" /></span>
			<span class="result-label" id="resultB"></span>
			</div>
		</div>
	</div>

	<div class="explain-steps">
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">サービス/コートサイド設定</span>
				<span class="explain-step-number">Step 3</span>
			</div>
			<p class="explain-step-description">各チームの選択を聞いたうえで、サービスとコートサイドの設定を行いましょう。</p>
		</div>
	</div>

	<div class="regu-container">
		<div class="court-display">
			<div class="left-display">左</div>
			<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 40 40" id="courtButton">
				<circle cx="20" cy="20" r="20" fill="#666666"/>
				<g fill="white" transform="translate(4, 10)">
					<path d="M20 2 L8 2 L8 0 L2 4 L8 8 L8 6 L20 6 Z"/>
					<path d="M12 14 L24 14 L24 12 L30 16 L24 20 L24 18 L12 18 Z"/>
				</g>
			</svg>
			<div class="right-display">右</div>
		</div>
		<div class="regu-main-container">
			<div class="regu-left-side">
				<div class="labelLeft active" id="labelLeft"><c:out value="${sessionScope.reguA.abbreviation}" /></div>
				<div class="serve-display active" id="serveLeft">先サーブ</div>
			</div>
			<div class="regu-middle-side">
			</div>
			<div class="regu-right-side">
				<div class="labelRight" id="labelRight"><c:out value="${sessionScope.reguB.abbreviation}" /></div>
				<div class="serve-display" id="serveRight">後サーブ</div>
			</div>
		</div>
	</div>
	
	<div class="explain-steps">
		<div class="explain-step">
			<p class="explain-step-description">次はチーム・選手紹介です。</p>
		</div>
	</div>

	<form action="judgeFC" method="post">
		<input type="hidden" id="isAreguTossWin" name="isAreguTossWin" value="1">
		<input type="hidden" id="serve" name="serve" value="1">
		<input type="hidden" id="court" name="court" value="1">
		<button type="submit" name="buttonId" value="p0004">チーム・選手紹介へ</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/202411SepakRefTool/js/coinToss.js"></script>
<script type="text/javascript" src="/202411SepakRefTool/js/updateServeCourt.js"></script>
</body>
</html>