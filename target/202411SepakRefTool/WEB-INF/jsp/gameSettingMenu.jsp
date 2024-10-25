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
	
	<form action="judgeFC" method="post">
		<div class="section">
			<div class="section-title">審判コール：</div>
			<div class="checkbox-group">
				<label>表示する <input type="checkbox" name="refree-call-mode" checked readonly></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">審判コール言語：</div>
			<div class="radio-group">
				<label>日本語 <input type="radio" name="refree-call-language" value="0" checked readonly></label>
				<label>英語 <input type="radio" name="refree-call-language" value="1" readonly></label>
				<label>タイ語 <input type="radio" name="refree-call-language" value="2" readonly></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">試合種目：</div>
			<div class="radio-group">
				<label>レグ <input type="radio" name="match-type" value="regu" checked></label>
				<label>ダブルス <input type="radio" name="match-type" value="doubles"></label>
				<label>クワッド <input type="radio" name="match-type" value="quad"></label>
				<label>チーム <input type="radio" name="match-type" value="team"></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">勝敗決定方式：</div>
			<div class="radio-group">
				<label>2セット先取 <input type="radio" name="game-resolution" value="2-sets-first" checked></label>
				<label>2セット引き分け有り <input type="radio" name="game-resolution" value="2-sets-draw"></label>
				<label>3セット流し <input type="radio" name="game-resolution" value="3-sets"></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">試合セット数上限：</div>
			<div class="radio-group">
				<label>1セットまで<input type="radio" name="match-end-condition" value="1"></label>
				<label>2セットまで <input type="radio" name="match-end-condition" value="2"></label>
				<label>3セットまで <input type="radio" name="match-end-condition" value="3" checked></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">得点上限：</div>
			<div class="dropdown-group">
				<label>1セット通常時(ポイント) <select name="1set-normal-end-point">
					<c:forEach var="i" begin="15" end="21">
						<option value="${i}" <c:if test="${i == (sessionScope.gameConfig.deuceStartScore1set + 1)}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>2セット通常時(ポイント) <select name="2set-normal-end-point">
					<c:forEach var="i" begin="15" end="21">
						<option value="${i}" <c:if test="${i == (sessionScope.gameConfig.deuceStartScore2set + 1)}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>3セット通常時(ポイント) <select name="3set-normal-end-point">
					<c:forEach var="i" begin="15" end="21">
						<option value="${i}" <c:if test="${i == (sessionScope.gameConfig.deuceStartScore3set + 1)}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>1セットデュース時(ポイント) <select name="1set-deuce-end-point">
					<c:forEach var="i" begin="15" end="25">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.maxScore1set}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>2セットデュース時(ポイント) <select name="2set-deuce-end-point">
					<c:forEach var="i" begin="15" end="25">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.maxScore2set}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
			<div class="dropdown-group">
				<label>3セットデュース時(ポイント) <select name="3set-deuce-end-point">
					<c:forEach var="i" begin="15" end="25">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.maxScore3set}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
		</div>
		
		<div class="section">
			<div class="section-title">コートサイドチェンジ条件：</div>
			<div class="dropdown-group">
				<label>3セット目(ポイント) <select name="3set-court-side-change-end-point">
					<c:forEach var="i" begin="7" end="15">
						<option value="${i}" <c:if test="${i == sessionScope.gameConfig.courtChangeScore}">selected</c:if>>${i}</option>
					</c:forEach>
				</select></label>
			</div>
		</div>
		<div class="section">
			<div class="section-title">その他の設定：</div>
			<div class="checkbox-group">
				<label>オフラインモード <input type="checkbox" name="offline-mode" readonly></label>
			</div>
		</div>
	
		<button type="submit" name="buttonId" value="p0006">戻る</button>
		<button type="submit" name="buttonId" value="p0306">変更する</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>