<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	alert('${notice_msg}');//문자열로 처리해야 되기 때문에 작은 따옴표가 있어야 한다.
	location.href='${notice_url}';
</script>