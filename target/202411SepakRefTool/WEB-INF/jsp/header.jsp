<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<header>
  <nav class="navbar">
    <div class="navbar-brand">
      <a href="#" class="toggle-btn">
      	<svg width="24" height="24" viewBox="0 0 24 24" fill="none">
		  <path d="M4 6.5C4 5.94772 4.44772 5.5 5 5.5H19C19.5523 5.5 20 5.94772 20 6.5C20 7.05228 19.5523 7.5 19 7.5H5C4.44772 7.5 4 7.05228 4 6.5Z" fill="#FF9900"/>
		  <path d="M4 12C4 11.4477 4.44772 11 5 11H19C19.5523 11 20 11.4477 20 12C20 12.5523 19.5523 13 19 13H5C4.44772 13 4 12.5523 4 12Z" fill="#FF9900"/>
		  <path d="M5 17.5C4.44772 17.5 4 17.9477 4 18.5C4 19.0523 4.44772 19.5 5 19.5H19C19.5523 19.5 20 19.0523 20 18.5C20 17.9477 19.5523 17.5 19 17.5H5Z" fill="#FF9900"/>
		</svg>
      </a>
      <span>セパタクロー東北オープン</span>
    </div>
    <div class="navbar-links">
      <ul>
        <li><a href="judgeFC?buttonId=p0101">試合基本情報</a></li>
        <!-- <li><a href="judgeFC?buttonId=p0102">タイムアウト</a></li> -->
        <li><a href="judgeFC?buttonId=p0103">選手交代</a></li>
        <li><a href="judgeFC?buttonId=p0104">サービス/コートサイド選択</a></li>
        <li><a href="judgeFC?buttonId=p0105">得点経過表</a></li>
        <li><a href="judgeFC?buttonId=p0106">試合設定</a></li>
      </ul>
    </div>
  </nav>
  <div id="gameId" hidden><c:out value="${ sessionScope.game.gameId }" /></div>
</header>
