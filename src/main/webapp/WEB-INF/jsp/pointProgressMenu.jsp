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
<link href="/202411SepakRefTool/css/pointProgressMenu-jsp.css" rel="stylesheet" />
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<div class="container">
	<h2>得点経過表</h2>
	
	<div class="scorebookContainer">
		<div class="scorebookHeadContainer">
			<table>
				<tr>
					<th><c:out value="${ sessionScope.reguA.abbreviation }" /></th>
					<th>vs</th>
					<th><c:out value="${ sessionScope.reguB.abbreviation }" /></th>
				</tr>
			</table>
		</div>
		
		<div class="setTitle">1SET</div>
		<div class="firstSetContainer">
			<table>
				<c:forEach var="event" items="${ requestScope.gameEventHistoryList }">
					<c:if test="${ event.setNum == 1 }">
						<tr>
		                    <c:choose>
		                        <c:when test="${ event.type == 'ADDPOINTS' }">
		                            <td class="${ event.isAreguGot ? 'active' : '' } ${ event.isAreguGot && event.isSequential ? 'continue' : '' }">
										<c:out value="${event.isAreguGot ? event.firstDetail : ''}" />
		                            </td>
		                            <td></td>
		                            <td class="${ !event.isAreguGot ? 'active' : '' } ${ !event.isAreguGot && event.isSequential ? 'continue' : '' }">
										<c:out value="${!event.isAreguGot ? event.firstDetail : ''}" />
		                            </td>
		                        </c:when>
		                        <c:when test="${ event.type == 'TIMEOUT' }">
		                            <td></td>
		                            <td>タイムアウト<br><c:out value="${ event.firstDetail }" />分間</td>
		                            <td></td>
		                        </c:when>
		                        <c:when test="${ event.type == 'PLAYERSUBSTITUTION' && event.isAreguGot }">
		                            <td>
		                            	<c:if test="${ event.isAreguGot }">
		                                    <span class="in">in: ${event.firstDetail}</span><br>
		                                    <span class="in">in: <c:out value="${ event.firstDetail }" /></span><br>
		                                    <span class="out">out: <c:out value="${ event.secondDetail }" /></span>
		                                </c:if>
	                                </td>
		                            <td>選手交代</td>
		                            <td>
		                                <c:if test="${ !event.isAreguGot }">
		                                    in: <c:out value="${ event.firstDetail }" />, out: <c:out value="${ event.secondDetail }" />
		                                </c:if>
		                            </td>
		                        </c:when>
		                        <c:otherwise>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                        </c:otherwise>
		                    </c:choose>
		                </tr>
		            </c:if>
		        </c:forEach>
			</table>
		</div>
		
		<div class="setTitle">2SET</div>
		<div class="secondSetContainer">
			<table>
				<c:forEach var="event" items="${ requestScope.gameEventHistoryList }">
					<c:if test="${ event.setNum == 2 }">
						<tr>
		                    <c:choose>
		                        <c:when test="${ event.type == 'ADDPOINTS' }">
		                            <td class="${ event.isAreguGot ? 'active' : '' } ${ event.isAreguGot && event.isSequential ? 'continue' : '' }">
										<c:out value="${ event.isAreguGot ? event.firstDetail : '' }" />
		                            </td>
		                            <td></td>
		                            <td class="${ !event.isAreguGot ? 'active' : '' } ${ !event.isAreguGot && event.isSequential ? 'continue' : '' }">
										<c:out value="${ !event.isAreguGot ? event.firstDetail : '' }" />
		                            </td>
		                        </c:when>
		                        <c:when test="${ event.type == 'TIMEOUT' }">
		                            <td></td>
		                            <td>タイムアウト<br>${event.firstDetail}分間</td>
		                            <td>タイムアウト<br><c:out value="${ event.firstDetail }" />分間</td>
		                            <td></td>
		                        </c:when>
		                        <c:when test="${ event.type == 'PLAYERSUBSTITUTION' && event.isAreguGot }">
		                            <td>
		                            	<c:if test="${ event.isAreguGot }">
		                                    in: <c:out value="${ event.firstDetail }" /><br>out: <c:out value="${ event.secondDetail }" />
		                                </c:if>
	                                </td>
		                            <td>選手交代</td>
		                            <td>
		                                <c:if test="${ !event.isAreguGot }">
		                                    <span class="in">in: <c:out value="${ event.firstDetail }" /></span><br>
		                                    <span class="out">out: <c:out value="${ event.secondDetail }" /></span>
		                                </c:if>
		                            </td>
		                        </c:when>
		                        <c:otherwise>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                        </c:otherwise>
		                    </c:choose>
		                </tr>
		            </c:if>
		        </c:forEach>
			</table>
		</div>
		<div class="setTitle">3SET</div>
		<div class="thirdSetContainer">
			<table>
				<c:forEach var="event" items="${ requestScope.gameEventHistoryList }">
					<c:if test="${ event.setNum == 3 }">
						<tr>
		                    <c:choose>
		                        <c:when test="${ event.type == 'ADDPOINTS' }">
		                            <td class="${ event.isAreguGot ? 'active' : '' } ${ event.isAreguGot && event.isSequential ? 'continue' : '' }">
										<c:out value="${ event.isAreguGot ? event.firstDetail : '' }" />
		                            </td>
		                            <td></td>
		                            <td class="${ !event.isAreguGot ? 'active' : '' } ${ !event.isAreguGot && event.isSequential ? 'continue' : '' }">
										<c:out value="${ !event.isAreguGot ? event.firstDetail : '' }" />
		                            </td>
		                        </c:when>
		                        <c:when test="${ event.type == 'TIMEOUT' }">
		                            <td></td>
		                            <td>タイムアウト<br><c:out value="${ event.firstDetail }" />分間</td>
		                            <td></td>
		                        </c:when>
		                        <c:when test="${ event.type == 'PLAYERSUBSTITUTION' && event.isAreguGot }">
		                            <td>
		                            	<c:if test="${ event.isAreguGot }">
		                                    in: <c:out value="${ event.firstDetail }" /><br>
											out: <c:out value="${ event.secondDetail }" />
		                                </c:if>
	                                </td>
		                            <td>選手交代</td>
		                            <td>
		                                <c:if test="${ !event.isAreguGot }">
		                                    <span class="in">in: <c:out value="${ event.firstDetail }" /></span><br>
		                                    <span class="out">out: <c:out value="${ event.secondDetail }" /></span>
		                                </c:if>
		                            </td>
		                        </c:when>
		                        <c:otherwise>
		                            <td></td>
		                            <td></td>
		                            <td></td>
		                        </c:otherwise>
		                    </c:choose>
		                </tr>
		            </c:if>
		        </c:forEach>
			</table>
		</div>
	</div>
	
	<p class="errorMsg"><c:out value = "${ requestScope.errorMsg }" /></p>
	<c:forEach var="msg" items="${ requestScope.errorMsgList }">
		<p class="errorMsg"><c:out value="${ msg }" /></p>
	</c:forEach>
	
	<form action="judgeFC" method="post">
		<button type="submit" name="buttonId" value="p0006">戻る</button>
	</form>
</div>


<%@ include file="footer.jsp" %>

<script type="text/javascript" src="/202411SepakRefTool/js/pointProgress.js"></script>
</body>
</html>