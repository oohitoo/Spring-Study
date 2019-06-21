<!-- src/webapp/main2.jsp -->
<%@page import="com.myspring.springBoard.dao.BDao"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<% 
	BDao dao = new BDao();
	dao.write("홍길동", "제목", "내용");
%>
<html>
<body>
	성공
</body>
</html>