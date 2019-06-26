<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WriteView</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>작성자</td>
			<td>내용</td>
			<td>삭제</td>
		</tr>
		<c:forEach var="listdto" items="${list}">
			<tr>
				<td>${listdto.id}</td>
				<td>${listdto.writer}</td>
				<td>${listdto.content}</td>
				<td><a href="delete?id=${listdto.id}">X</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="writeview">글 쓰기</a>
</body>
</html>