<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>審判支援システム</title>
<link href="/202411SepakRefTool/css/header-non-menu.css" rel="stylesheet" />
<style>
    button[type="submit"] {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 200px;
        padding: 22px 5px;
        margin: 0 auto;
        border: none;
        border-radius: 6px;
        box-sizing: border-box;
        background-color: #ff9900;
        color: #333;
        cursor: pointer;
        transition: background-color 0.3s ease;
        font-weight: bold;
    }

    button[type="submit"]:hover {
        background-color: #ff7700;
    }
</style>
</head>
<body>
<%@ include file="headerNonMenu.jsp" %>
<p>システムエラーです。</p>

<c:out value = "${ requestScope.errorMsg }" />
<c:forEach var="msg" items="${ requestScope.errorMsgList }">
	<p><c:out value="${ msg }" /></p>
</c:forEach>

<form action="judgeFC" method="post">
    <button type="submit" name="buttonId" value="p0000">ログイン画面へ</button>
</form>

<%@ include file="footer.jsp" %>

</body>
</html>