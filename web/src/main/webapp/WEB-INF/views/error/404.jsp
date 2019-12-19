<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <p>Вы где то свернули не туда.... Вам рекомендуется вернутся в <a href="${contextPath}/hello/">начало</a>.</p>
</head>
<body background="${contextPath}/resources/images/404.jpg">
</body>
</html>
