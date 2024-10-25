<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header>
	<nav class="navbar">
		<span>セパタクロー東北オープン</span>
	</nav>
	
	<div id="setNow" hidden><c:out value="${ sessionScope.game.setNow }" /></div>
	<div id="gameId" hidden><c:out value="${ sessionScope.game.gameId }" /></div>
</header>