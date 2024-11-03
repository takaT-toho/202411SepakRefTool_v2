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
<link href="/202411SepakRefTool/css/gameSettingMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>試合設定</h2>
	

	<!-- 試合設定情報を取得する -->
	
	<form action="judgeFC" method="post" id="form">
		<div class="section">
			<div class="section-title">審判コール：</div>
			<div class="checkbox-group">
				<label>表示する <input type="checkbox" name="refree-call-mode" checked disabled></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">審判コール言語：</div>
			<div class="radio-group">
				<label>日本語 <input type="radio" name="refree-call-language" value="0" checked disabled></label>
				<label>英語 <input type="radio" name="refree-call-language" value="1" disabled></label>
				<label>タイ語 <input type="radio" name="refree-call-language" value="2" disabled></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">試合種目：</div>
			<div class="radio-group">
				<label>レグ <input type="radio" name="match-type" value="regu" checked disabled></label>
				<label>ダブルス <input type="radio" name="match-type" value="doubles" disabled></label>
				<label>クワッド <input type="radio" name="match-type" value="quad" disabled></label>
				<label>チーム <input type="radio" name="match-type" value="team" disabled></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">勝敗決定方式：</div>
			<div class="radio-group">
				<label>2セット先取 <input type="radio" name="game-resolution" value="2-sets-first" disabled></label>
				<label>2セット引き分け有り <input type="radio" name="game-resolution" value="2-sets-draw" checked disabled></label>
				<label>3セット流し <input type="radio" name="game-resolution" value="3-sets" disabled></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">試合セット数上限：</div>
			<div class="radio-group">
				<label>1セットまで<input type="radio" name="match-end-condition" value="1" disabled></label>
				<label>2セットまで <input type="radio" name="match-end-condition" value="2" checked disabled></label>
				<label>3セットまで <input type="radio" name="match-end-condition" value="3" disabled></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">得点上限：</div>
			<div class="dropdown-group">
				<label>1セット通常時(ポイント) <select name="1set-normal-end-point" disabled>
					<c:forEach var="i" begin="15" end="21">
						<option value="${i}" <c:if test="${i == (sessionScope.gameConfig.deuceStartScore1set + 1)}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>2セット通常時(ポイント) <select name="2set-normal-end-point" disabled>
					<c:forEach var="i" begin="15" end="21">
						<option value="${i}" <c:if test="${i == (sessionScope.gameConfig.deuceStartScore2set + 1)}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>3セット通常時(ポイント) <select name="3set-normal-end-point" disabled>
					<c:forEach var="i" begin="15" end="21">
						<option value="${i}" <c:if test="${i == (sessionScope.gameConfig.deuceStartScore3set + 1)}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>1セットデュース時(ポイント) <select name="1set-deuce-end-point" disabled>
					<c:forEach var="i" begin="15" end="25">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.maxScore1set}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>2セットデュース時(ポイント) <select name="2set-deuce-end-point" disabled>
					<c:forEach var="i" begin="15" end="25">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.maxScore2set}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>3セットデュース時(ポイント) <select name="3set-deuce-end-point" disabled>
					<c:forEach var="i" begin="15" end="25">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.maxScore3set}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
		</div>
		
		<div class="section">
			<div class="section-title">コートサイドチェンジ条件：</div>
			<div class="dropdown-group">
				<label>3セット目(ポイント) <select name="3set-court-side-change-end-point" disabled>
					<c:forEach var="i" begin="7" end="15">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.courtChangeScore}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">その他の設定：</div>
			<div class="checkbox-group">
				<label>オフラインモード <input type="checkbox" name="offline-mode" disabled></label>
			</div>
		</div>
	
		<button type="submit" name="buttonId" value="p0006">戻る</button>
		<button type="submit" name="buttonId" value="p0306">変更する</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script>
	document.querySelector("form").addEventListener("submit", function() {
		document.getElementById("refree-call-mode").disabled = false;
		document.querySelectorAll("input[name='refree-call-language']").forEach(radio => {
			radio.disabled = false;
		});
		document.querySelectorAll("input[name='match-type']").forEach(radio => {
			radio.disabled = false;
		});
		document.querySelectorAll("input[name='game-resolution']").forEach(radio => {
			radio.disabled = false;
		});
		document.querySelectorAll("input[name='match-end-condition']").forEach(radio => {
			radio.disabled = false;
		});
		document.querySelectorAll("select").forEach(select => {
			select.disabled = false;
		});
		document.getElementById("offline-mode").disabled = false;
		
	});
  </script>
</body>
</html>