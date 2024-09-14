<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>審判支援システム</title>
<link href="/demo202411/css/header-non-menu.css" rel="stylesheet" />
<link href="/demo202411/css/form.css" rel="stylesheet" />
<link href="/demo202411/css/gameSettingMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>試合設定</h2>
	
	
	<div class="section">
	    <div class="section-title">審判コール：</div>
	    <div class="checkbox-group">
	        <label>表示する <input type="checkbox" name="refree-call-mode" checked></label>
	    </div>
	</div>
	<div class="section">
	    <div class="section-title">審判コール言語：</div>
	    <div class="radio-group">
	        <label>日本語 <input type="radio" name="refree-call-language" value="Japanese" checked></label>
	        <label>英語 <input type="radio" name="refree-call-language" value="English"></label>
	        <label>タイ語 <input type="radio" name="refree-call-language" value="Thai"></label>
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
	        <label>1セットまで<input type="radio" name="match-end-condition" value="2-sets-first"></label>
	        <label>2セットまで <input type="radio" name="match-end-condition" value="2-sets-draw"></label>
	        <label>3セットまで <input type="radio" name="match-end-condition" value="3-sets" checked></label>
	    </div>
	</div>
	<div class="section">
	    <div class="section-title">得点上限：</div>
	    <div class="dropdown-group">
	        <label>1セット通常時(ポイント) <select name="1set-normal-end-point">
	            <option value="1">1</option>
	            <option value="2">2</option>
	            <option value="3">3</option>
	            <option value="4">4</option>
	            <option value="5">5</option>
	            <option value="6">6</option>
	            <option value="7">7</option>
	            <option value="8">8</option>
	            <option value="9">9</option>
	            <option value="10">10</option>
	            <option value="11">11</option>
	            <option value="12">12</option>
	            <option value="13">13</option>
	            <option value="14">14</option>
	            <option value="15">15</option>
	            <option value="16">16</option>
	            <option value="17">17</option>
	            <option value="18">18</option>
	            <option value="19">19</option>
	            <option value="20">20</option>
	            <option value="21" selected>21</option>
	        </select></label>
	    </div>
	    <div class="dropdown-group">
	        <label>2セット通常時(ポイント) <select name="2set-normal-end-poin">
	            <option value="1">1</option>
	            <option value="2">2</option>
	            <option value="3">3</option>
	            <option value="4">4</option>
	            <option value="5">5</option>
	            <option value="6">6</option>
	            <option value="7">7</option>
	            <option value="8">8</option>
	            <option value="9">9</option>
	            <option value="10">10</option>
	            <option value="11">11</option>
	            <option value="12">12</option>
	            <option value="13">13</option>
	            <option value="14">14</option>
	            <option value="15">15</option>
	            <option value="16">16</option>
	            <option value="17">17</option>
	            <option value="18">18</option>
	            <option value="19">19</option>
	            <option value="20">20</option>
	            <option value="21" selected>21</option>
	        </select></label>
	    </div>
	    <div class="dropdown-group">
	        <label>3セット通常時(ポイント) <select name="3set-normal-end-poin">
	            <option value="1">1</option>
	            <option value="2">2</option>
	            <option value="3">3</option>
	            <option value="4">4</option>
	            <option value="5">5</option>
	            <option value="6">6</option>
	            <option value="7">7</option>
	            <option value="8">8</option>
	            <option value="9">9</option>
	            <option value="10">10</option>
	            <option value="11">11</option>
	            <option value="12">12</option>
	            <option value="13">13</option>
	            <option value="14">14</option>
	            <option value="15">15</option>
	            <option value="16">16</option>
	            <option value="17">17</option>
	            <option value="18">18</option>
	            <option value="19">19</option>
	            <option value="20">20</option>
	            <option value="21" selected>21</option>
	        </select></label>
	    </div>
	    <div class="dropdown-group">
	        <label>1セットデュース時(ポイント) <select name="1set-deuce-end-point">
	            <option value="1">1</option>
	            <option value="2">2</option>
	            <option value="3">3</option>
	            <option value="4">4</option>
	            <option value="5">5</option>
	            <option value="6">6</option>
	            <option value="7">7</option>
	            <option value="8">8</option>
	            <option value="9">9</option>
	            <option value="10">10</option>
	            <option value="11">11</option>
	            <option value="12">12</option>
	            <option value="13">13</option>
	            <option value="14">14</option>
	            <option value="15">15</option>
	            <option value="16">16</option>
	            <option value="17">17</option>
	            <option value="18">18</option>
	            <option value="19">19</option>
	            <option value="20">20</option>
	            <option value="21">21</option>
	            <option value="22">22</option>
	            <option value="23">23</option>
	            <option value="24">24</option>
	            <option value="25" selected>25</option>
	        </select></label>
	    </div>
	    <div class="dropdown-group">
	        <label>2セットデュース時(ポイント) <select name="2set-deuce-end-poin">
	            <option value="1">1</option>
	            <option value="2">2</option>
	            <option value="3">3</option>
	            <option value="4">4</option>
	            <option value="5">5</option>
	            <option value="6">6</option>
	            <option value="7">7</option>
	            <option value="8">8</option>
	            <option value="9">9</option>
	            <option value="10">10</option>
	            <option value="11">11</option>
	            <option value="12">12</option>
	            <option value="13">13</option>
	            <option value="14">14</option>
	            <option value="15">15</option>
	            <option value="16">16</option>
	            <option value="17">17</option>
	            <option value="18">18</option>
	            <option value="19">19</option>
	            <option value="20">20</option>
	            <option value="21">21</option>
	            <option value="22">22</option>
	            <option value="23">23</option>
	            <option value="24">24</option>
	            <option value="25" selected>25</option>
	        </select></label>
	    </div>
	    <div class="dropdown-group">
	        <label>3セットデュース時(ポイント) <select name="3set-deuce-end-poin">
	            <option value="1">1</option>
	            <option value="2">2</option>
	            <option value="3">3</option>
	            <option value="4">4</option>
	            <option value="5">5</option>
	            <option value="6">6</option>
	            <option value="7">7</option>
	            <option value="8">8</option>
	            <option value="9">9</option>
	            <option value="10">10</option>
	            <option value="11">11</option>
	            <option value="12">12</option>
	            <option value="13">13</option>
	            <option value="14">14</option>
	            <option value="15">15</option>
	            <option value="16">16</option>
	            <option value="17">17</option>
	            <option value="18">18</option>
	            <option value="19">19</option>
	            <option value="20">20</option>
	            <option value="21">21</option>
	            <option value="22">22</option>
	            <option value="23">23</option>
	            <option value="24">24</option>
	            <option value="25" selected>25</option>
	        </select></label>
	    </div>
	</div>
	
	<div class="section">
	    <div class="section-title">コートサイドチェンジ条件：</div>
	    <div class="dropdown-group">
	        <label>3セット目(ポイント) <select name="3set-court-side-change-end-poin">
	            <option value="1">1</option>
	            <option value="2">2</option>
	            <option value="3">3</option>
	            <option value="4">4</option>
	            <option value="5">5</option>
	            <option value="6">6</option>
	            <option value="7">7</option>
	            <option value="8">8</option>
	            <option value="9">9</option>
	            <option value="10">10</option>
	            <option value="11" selected>11</option>
	            <option value="12">12</option>
	            <option value="13">13</option>
	            <option value="14">14</option>
	            <option value="15">15</option>
	            <option value="16">16</option>
	            <option value="17">17</option>
	            <option value="18">18</option>
	            <option value="19">19</option>
	            <option value="20">20</option>
	            <option value="21">21</option>
	        </select></label>
	    </div>
	</div>
	<div class="section">
	    <div class="section-title">その他の設定：</div>
	    <div class="checkbox-group">
	        <label>オフラインモード <input type="checkbox" name="offline-mode"></label>
	    </div>
	</div>
	
	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0006">戻る</button>
	</form>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>