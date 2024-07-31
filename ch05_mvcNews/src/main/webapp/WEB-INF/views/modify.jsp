<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check}">
	<script>
		alert('글 수정을 완료했습니다.');
		location.href='detail.do?num=${num}';//수정한 후에 상세페이지로 이동. request에 num을 넣어놨기 때문에 JSP에서 가져다 쓸 수 있다.
	</script>
</c:if>
<c:if test="${!check}">
	<script>
		alert('비밀번호 불일치');
		history.go(-1);
	</script>
</c:if>