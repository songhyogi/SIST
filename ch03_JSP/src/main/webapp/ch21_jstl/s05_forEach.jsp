<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forEach 태그</title>
</head>
<body>
<h4>1부터 100까지의 홀수의 합</h4>
<c:set var="sum" value="0"/>
<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="sum" value="${sum + i}"/> <%-- set태그로 sum변수에 value에서 el로 연산해 저장 --%>
</c:forEach>
결과 = ${sum}

<h4>구구단 : 5단</h4>
<ul>
	<c:forEach var="i" begin="1" end="9">
		<li>5 * ${i} = ${5 * i}</li> <%--연산은 el이 해준다. --%>
	</c:forEach>
</ul>

<h4>int형 배열</h4>
<c:set var="intArray" value="<%= new int[]{10,20,30,40,50} %>"/><%-- set 태그는 배열을 생성할 수 있는 능력이 없기 때문에 표현식을 사용 --%>
<c:forEach var="i" items="${intArray}" begin="2" end="4" varStatus="status"> <%-- items로 객체를 읽어와서 i에 값을 넣어준다. (확장 for문과 유사)--%>
	
	${status.index} - ${status.count} - [${i}] - ${status.first} - ${status.last} <br>
</c:forEach>

<h4>Map</h4>
<%
//생성하는 것은 el과 jstl이 할 수 없기 때문에 스크립트릿을 사용
	HashMap<String,String> mapData = new HashMap<String,String>();
	mapData.put("name","홍길동");
	mapData.put("job","소방관");
	mapData.put("hobby","영화감상");
%>
<c:set var="map" value="<%= mapData %>"/><%-- 스크립트릿에 생성한 것을 el태그와 jstl태그에서 사용하려면 페이지 영역에 저장이 되어있어야 한다. --%>
<c:forEach var="i" items="${map}"><%-- el이 읽어옴 속성명(var)에서 지정한 map --%>
	${i.key} = ${i.value}<br> <%-- 키와 밸류의 쌍으로 출력 --%>
</c:forEach>
</body>
</html>