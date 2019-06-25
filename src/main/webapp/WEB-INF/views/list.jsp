<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="500" border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>제목</th>
		<th>날짜</th>
		<th>히트</th>
	</tr>
	<c:forEach items="${list}" var="dto">
	<tr>
		<td>${dto.bId}</td>
		<td>${dto.bName}</td>
		<td><a href="contentView?bId=${dto.bId}">${dto.bTitle}</a></td>
		<td>${dto.bDate}</td>
		<td>${dto.bHit}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5"><a href="writeView">글작성</a></td>
	</tr>
</table>
	
</body>
</html>