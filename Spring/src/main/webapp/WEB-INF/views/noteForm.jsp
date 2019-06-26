<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="wirte" method="post">
	<table border="1">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="Notetitle" value="한글"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><input type="text" name="NoteContent" value="내용"></td>
		</tr>
		<tr>
			<td><input type="submit" value="입력"></td>
			<td><a href="NoteList">목록보기</a></td>
		</tr>
	</table>
</form>
</body>
</html>