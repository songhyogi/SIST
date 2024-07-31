<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 등록 완료</title>
</head>
<body>
리포트 <b>${report.subject}</b>를 등록했습니다.<br>
업로드한 파일 : ${report.reportFile.originalFilename}<br><!-- report.reportFile : MultipartFile 객체 반환. 그 안에 originalFilename이 있는데 이걸 프로퍼티명 형식으로 표시-->
용량 : ${report.reportFile.size}
</body>
</html>