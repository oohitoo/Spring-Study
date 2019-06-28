<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예매 확인</title>
</head>
<body>
	<c:if test="${flag == true}">
		<font color="blue">티켓이 발행되었습니다.</font><br>
		고객 아이디 : ${tick_info.counsumerId} <br>
		티켓 구매수 : ${tick_info.amount}<br>
		<a href="buy_ticket">야구 예매 하러 가기</a>
	</c:if>
	<c:if test="${flag == false}">
		<font color="red">카드 결제가 취소 되었습니다.</font><br>
		<a href="buy_ticket">야구 예매 하러 가기</a>
	</c:if>		
</body>
</html>