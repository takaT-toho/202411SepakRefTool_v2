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
<link href="/202411SepakRefTool/css/rps-jsp.css" rel="stylesheet" />

</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<div class="rps-container">
	  <p>じゃんけんの勝ちレグを選択してください。</p>
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
		
	<div class="callMsgContainer">
		<div class="callMsgLine"></div>
		<div class="callMsgArea">
			<div id="callMsg">メッセージ</div>
		</div>
		<div class="callMsgLine"></div>
	</div>
	
	<form action="/202411SepakRefTool/judgeFC" method="post" id="checkForm">
        <input type="text" name="isAreguGameWin" id="isAreguGameWin" value="" hidden></span>
		<button type="submit" name="buttonId" value="p0205">試合を終了する</button>
	</form>
	<div class="errorMsg" id="errorMsg"></div>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/202411SepakRefTool/js/rps-jsp.js"></script>
</body>
</html>