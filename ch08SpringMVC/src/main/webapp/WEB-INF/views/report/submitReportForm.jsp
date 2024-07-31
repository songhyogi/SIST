<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 등록 폼</title>
</head>
<body>
<form:form action="submitReport.do" enctype="multipart/form-data" modelAttribute="report"><!-- 파일 업로드하는 거니까 enctype 필요 -->
	<ul>
		<li>
			<form:label path="subject">제목</form:label>
			<form:input path="subject"/>
			<form:errors path="subject"/>
		</li>
		<li>
			<form:label path="reportFile">리포트 파일</form:label>
			<input type="file" id="reportFile" name="reportFile"><!-- input type="file"은 커스텀태그가 없다. -->
			<form:errors path="reportFile"/>
		</li>
		<li>
			<form:button>리포트 전송</form:button>
		</li>
	</ul>
</form:form>
</body>
</html>