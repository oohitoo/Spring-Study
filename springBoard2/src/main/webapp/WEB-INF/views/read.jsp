<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update">
	<table border="1">
		<tr>
			<td>번호</td>
			<td><input type="text" name="boardid" value="${readContent.id}" readonly="readonly"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="boardname" value="${readContent.name}" ></td>
		</tr>
		<tr>
			<td>타이틀</td>
			<td><input type="text" name="boardtitle" value="${readContent.title}" ></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><input type="text" name="boardcontent" value="${readContent.content}" ></td>
		</tr>
		<tr>
			<td><input type="submit" value="수정"></td>
			<td><a href="boardList">목록보기</a></td>
		</tr>
	</table>
</form>
</body>
</html>