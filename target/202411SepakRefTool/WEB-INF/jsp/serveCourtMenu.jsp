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
<link href="/202411SepakRefTool/css/serveCourtMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>サービス/コートサイド選択</h2>

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
				<c:if test="${sessionScope.gameConfig.isAreguLeft == sessionScope.gameConfig.isAreguFirstServe}">
					<c:if test="${sessionScope.gameConfig.isAreguLeft}">
						<div class="labelLeft active" id="labelLeft"><c:out value="${sessionScope.reguA.abbreviation}" /></div>
					</c:if>
					<c:if test="${!sessionScope.gameConfig.isAreguLeft}">
						<div class="labelLeft active" id="labelLeft"><c:out value="${sessionScope.reguB.abbreviation}" /></div>
					</c:if>
					<div class="serve-display active" id="serveLeft">先サーブ</div>
				</c:if>				
				<c:if test="${sessionScope.gameConfig.isAreguLeft != sessionScope.gameConfig.isAreguFirstServe}">
					<c:if test="${sessionScope.gameConfig.isAreguLeft}">
						<div class="labelLeft" id="labelLeft"><c:out value="${sessionScope.reguA.abbreviation}" /></div>
					</c:if>
					<c:if test="${!sessionScope.gameConfig.isAreguLeft}">
						<div class="labelLeft" id="labelLeft"><c:out value="${sessionScope.reguB.abbreviation}" /></div>
					</c:if>
					<div class="serve-display" id="serveLeft">後サーブ</div>
				</c:if>
			</div>
			<div class="regu-middle-side">
			</div>
			<div class="regu-right-side">
				<c:if test="${sessionScope.gameConfig.isAreguLeft == sessionScope.gameConfig.isAreguFirstServe}">
					<c:if test="${sessionScope.gameConfig.isAreguLeft}">
						<div class="labelRight" id="labelRight"><c:out value="${sessionScope.reguB.abbreviation}" /></div>
					</c:if>
					<c:if test="${!sessionScope.gameConfig.isAreguLeft}">
						<div class="labelRight" id="labelRight"><c:out value="${sessionScope.reguA.abbreviation}" /></div>
					</c:if>
					<div class="serve-display" id="serveRight">後サーブ</div>
				</c:if>
				<c:if test="${sessionScope.gameConfig.isAreguLeft != sessionScope.gameConfig.isAreguFirstServe}">
					<c:if test="${sessionScope.gameConfig.isAreguLeft}">
						<div class="labelRight active" id="labelRight"><c:out value="${sessionScope.reguB.abbreviation}" /></div>
					</c:if>
					<c:if test="${!sessionScope.gameConfig.isAreguLeft}">
						<div class="labelRight active" id="labelRight"><c:out value="${sessionScope.reguA.abbreviation}" /></div>
					</c:if>
					<div class="serve-display active" id="serveRight">先サーブ</div>
				</c:if>
			</div>
		</div>
	</div>
	
	<form action="judgeFC" method="post">
		<input type="hidden" id="serve" name="serve" 
		value=<c:out value="${ sessionScope.gameConfig.isAreguFirstServe ? 1 : 0 }" />>
		<input type="hidden" id="court" name="court" value=<c:out value="${ sessionScope.gameConfig.isAreguLeft ? 1 : 0 }" />>
		<button type="submit" name="buttonId" value="p0006">キャンセル</button>
		<button type="submit" name="buttonId" value="p0304">変更する</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/202411SepakRefTool/js/updateServeCourt.js"></script>
</body>
</html>