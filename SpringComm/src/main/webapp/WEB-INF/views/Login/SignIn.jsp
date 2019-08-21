<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티(로그인)</title>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="resources/css/SignInCss.css" type="text/css"/>
<script src="resources/js/loginScript.js"></script>
</head>
<body>

<div class="container">
    	<div class="row">    	
			<div class="col-md-6 col-md-offset-3">
				<div class="col-xs-12 col-sm-12">
			      <a href="Index"><img style="margin:5px auto;" height="150px" width="150px" am-TopLogo alt="SECUREVIEW"  src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQptxd7RC0LxOgDp1SyWc1PHnZEMJgySONcV-ET_yxO06sgBccA" class="img-responsive"></a>
			    </div>
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">로그인</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">회원가입</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<!-- 로그인 -->
								<form id="login-form" name="loginuser" method="GET" role="form" style="display: block;">
									<div class="form-group">
										<label> ID </label>
										<input type="text" name="userid" class="form-control" placeholder="아이디 입력">
									</div>
									<div class="form-group">
										<label> PWD </label>
										<input type="password" name="userpwd" class="form-control" placeholder="패스워드 입력">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="button" id="login-submit" name="loginsubmit" class="form-control btn btn-login" data-toggle="modal fade">로그인</button>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="findid" tabindex="5">아이디 찾기</a>&#47;
													<a href="findpwd" tabindex="5">패스워드 찾기</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								
								<!-- 회원가입 -->
								<form id="register-form" name="createuser" action="createuser" method="POST" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="createuserid" class="form-control" placeholder="아이디는 5자리에서 15자리 사이로 입력해주세요">
									</div>
									<div class="form-group">
										<input type="password" name="createuserpwd" class="form-control" placeholder="패스워드입력해주세요">
									</div>
									<div class="form-group">
										<input type="email" name="createuseremail"class="form-control" placeholder="이메일을 입력해주세요">
									</div>
									<div class="form-group">
										<input type="text" name="createusername" id="confirm-password" class="form-control" placeholder="닉네임을 입력해주세요">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="button" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" data-toggle="modal fade">회원 가입</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

		<!-- 회원가입 실패쪽 모달창 -->
		<div class="modal fade example" id="layerpop" >
		  <div class="modal-dialog">
				<div class="modal-content">
					<!-- header -->
					<div class="modal-header">
						<!-- 닫기(x) 버튼 -->
						<button type="button" class="close" data-dismiss="modal">×</button>
						<!-- header title -->
						<h4 class="modal-title">회원가입 실패</h4>
					</div>
					<!-- body -->
					<div class="modal-body">
						입력한 정보를 다시 확인해주세요!
					</div>
					<!-- Footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>


	<!-- 로그인쪽 모달창 -->
	<div class="modal fade login" id="layerpop">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- header -->
				<div class="modal-header">
					<!-- 닫기(x) 버튼 -->
					<button type="button" class="close" data-dismiss="modal">×</button>
					<!-- header title -->
					<h4 class="modal-title">로그인 실패</h4>
				</div>
				<!-- body -->
				<div class="modal-body">
					아이디 또는 패스워드가 일치 하지 않습니다.
				</div>
				<!-- Footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
</body>
</html>