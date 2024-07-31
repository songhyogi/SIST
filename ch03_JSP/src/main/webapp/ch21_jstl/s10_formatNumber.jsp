<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formatNumber 태그</title>
</head>
<body>
숫자 : <fmt:formatNumber type="number" value="1000000"/><br>
통화 : <fmt:formatNumber type="currency" value="1000000" currencySymbol="$"/><br>
통화 : <fmt:formatNumber type="currency" value="1000000" currencySymbol="\\"/><br> <%-- \(원 표시)를 하려고 하면 특수문자이기 때문에 \\두개로 표시해줘야한다. --%>
퍼센트 : <fmt:formatNumber type="percent" value="0.3"/><br>
패턴 : <fmt:formatNumber value="12.345" pattern="00.00"/><br>
</body>
</html>