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
<link href="/202411SepakRefTool/css/twoMinutesWarmUp-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>

<div class="container">
	<div class="explain-card-header">
		<h2>2分間休憩</h2>
	</div>
	<div class="explain-steps">
		<div class="explain-step">
			<div class="explain-step-header">
				<span class="explain-step-title">主審の行動</span>
				<span class="explain-step-number">Step 1</span>
			</div>
			<p class="explain-step-description">2分間のタイマーを開始してください。</p>
		</div>
	</div>

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

	
	<div class="explain-steps">
		<div class="explain-step">
			<p class="explain-step-description">2分休憩が終わったら試合を開始します</p>
			<div class="explain-quote">
				<p>"レディー！"</p>
			</div>
		</div>
	</div>

	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0202">次のセットへ</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/202411SepakRefTool/js/normalTimer.js"></script>
</body>
</html>