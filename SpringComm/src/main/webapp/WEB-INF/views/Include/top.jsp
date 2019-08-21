<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<body>
<div class="container">
	<!-- Topper w/ logo -->
  <div class="row hidden-xs topper">
    <div class="col-xs-10 col-sm-10">
      <a href="Index"><img am-TopLogo alt="SECUREVIEW"  src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQptxd7RC0LxOgDp1SyWc1PHnZEMJgySONcV-ET_yxO06sgBccA" class="img-responsive"></a>
    </div>
    <c:if test="${sessionScope.idKey == null}">
    <div class="col-xs-2 col-sm-2">
      <h4>
	      <a href="SignIn">로그인 </a> &nbsp;
	      <a href="SignIn">회원가입 </a>
      </h4>
    </div>
    </c:if>
    <c:if test="${sessionScope.idKey != null}">
    	<h4>${sessionScope.idKey}님 반갑습니다.</h4>
    <div class="col-xs-2 col-sm-2">    
      <h4>
      	<a href="logout">로그아웃 </a>
      </h4>
    </div>
    </c:if>
  </div> <!-- End Topper -->
</body>
</html>