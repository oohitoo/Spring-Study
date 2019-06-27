<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트 보기</title>
</head>
<body>
	<table width="500" border="1">
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>내용</th>
		<th>삭제</th>
	</tr>
	<c:forEach items="${noteList}" var="notedto">
	<tr>
		<td>${notedto.id}</td>
		<td>${notedto.writer}</td>
		<td>${notedto.content}</td>
		<td><a href="delete?id=${notedto.id}">X</a></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5"><a href="writerForm">글작성</a></td>
	</tr>
</table>
</body>
</html>