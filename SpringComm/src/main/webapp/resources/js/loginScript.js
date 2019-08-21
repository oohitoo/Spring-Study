$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
	$('#login-submit').click(function(e) {
		if($('[name="userid"]').val() == 0 || $('[name="userpwd"]').val() == 0){
			$('div.modal.login').modal();
		}else{
			/*$('#login-form').submit();*/
			var param = $("form[name=loginuser]").serialize();
			console.log(param);
			$.ajax({
				url: "loginuser",
				type: "GET",
				data: param,
				success:function(data){
					/*console.log(data);
					alert(data);*/
					if(data == 1){
						console.log("로그인 실패");
						$('div.modal.login').modal();
					}else{
						window.location.href = "Index";
					}
				},
				error:function(data){
					alert("error");
				}
			});
		}
	});
		
	$('#register-submit').click(function() {
		// 정규식 - 이메일 유효성 검사
        var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        // 정규식 -전화번호 유효성 검사
        var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
        // 정규식 - 영어 + 숫자
        var idReg = /^[a-z]+[a-z0-9]{5,14}$/g;
		
        if(!idReg.test($('[name="createuserid"]').val())){
        	$('[name="createuserid"]').focus();
        	alert("아이디는 영문자로 시작하는 6~15자 영문자 또는 숫자이어야 합니다.");
            return;
        }
        
		if($('[name="createuserpwd"]').val() == "" || $('[name="createuserpwd"]').val() == 0){
			$('[name="createuserpwd"]').focus();
			$('div.modal.example').modal();
			return;
		}
		
        if(!regEmail.test($('[name="createuseremail"]').val())) {
            $('[name="createuseremail"]').focus();
            $('div.modal.example').modal();
    		return;
        }
        
		if($('[name="createusername"]').val() == ""){
			$('[name="createusername"]').focus();
			$('div.modal.example').modal();
			return;
		}
		$('#register-form').submit();
	});
	
	// 여기서 부터 방명록에 대한 Jquery
	$('#commentSubmit').click(function(e) {	
	
		var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
			
		if(!special_pattern.test($('[name="commentuser"]').val()) == 0 || !special_pattern.test($('[name="content"]').val()) == 0){
			$('div.modal').modal();
		}else{
			if ($("input:checkbox[name='secret']").is(":checked") == true){
				$('.secret').val('0');
				console.log("체크됨");
			}else{
				$('.secret').val('1');
				console.log("체크안됨");
			}
			
			var comment = $("form[name=commentInsert]").serialize();
			var uri = $(location).attr('search');
			
			/*console.log(comment);*/
			console.log(comment);
			console.log("url : "+uri);
			
			$.ajax({
				url: "commentInsert",
				type: "POST",
				data: comment + "&uri="+uri,
				success:function(data){
					/*console.log($(location).attr('pathname')+""+$(location).attr('search'));*/
					
					/*$("#listGuestBook").load("Comment #listcomm");*/
					$("#listGuestBook").load("#listGuestBook #listcomm");			
					$('#insertForm')[0].reset();
					
				},
				error:function(request,status,error){
				    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    alert("실패");
				}
			});
		}
	});
});