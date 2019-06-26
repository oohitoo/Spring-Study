<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>타이틀</td>
			<td>내용</td>
			<td>날짜</td>
			<td>조회수</td>
			<td>삭제</td>
		</tr>
		<c:forEach var="dao" items="${boardlist}">
			<tr>
				<td>${dao.id}</td>
				<td>${dao.name}</td>
				<td><a href="read?id=${dao.id}">${dao.title}</a></td>
				<td>${dao.content}</td>
				<td>${dao.regdate}</td>
				<td>${dao.hit}</td>
				<td><a href="delete?id=${dao.id}">X</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href ="writeView">글 쓰기</a>
</body>
</html>