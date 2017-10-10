function userLogin(){
		var name=$('#count').val().trim();
		var password=$('#password').val().trim();
		$('#count_span').html("");
		$('#password_span').html("");
		var ok=true;
		if(name==""){
			$('#count_span').html("用户名不能为空！");
			ok=false;
		}
		if(password==""){
			$('#password_span').html("密码不能为空！");
			ok=false;
		}
		//发送请求
		if(ok){//检测格式通过，发送ajax请求
			$.ajax({
				url:path+"/user/login.do",
				type:"post",
				data:{"name":name,"password":password},
				dataType:"json",
				success:function(result){
					//result是服务器返回的json数据
					if(result.status==0){
						//将用户信息保存到cookie中
						var userId=result.data.cn_user_id;
						addCookie("userId",userId,2);
						window.location.href="edit.html";
					}else if(result.status==1){
						$('#count_span').html(result.msg);
					}else if(result.status==2){
						$('#password_span').html(result.msg);
					}
				},
				error:function(){
					alert("登录失败！");
				}
			});
		}			
}

function userRegist(){
	//获取参数
	var name=$('#regist_username').val().trim();
	var nick=$('#nickname').val().trim();
	var password=$('#regist_password').val().trim();
	var final_password=$('#final_password').val().trim();
	//数据格式检测
	var ok=true;
	if(name==""){
		$('#warning_1 span').html("用户不能为空！");
		$('#warning_1').show();
		ok=false;
	}
	if(password==""){
		$('#warning_2 span').html("密码不能为空！");
		$('#warning_2').show();
		ok=false;
	}else{
		var n=password.length;
		if(n<6){
			$('#warning_2').show();
			ok=false;
		}
		if(password!=final_password){
			$('#warning_3').show();
			ok=false;
		}
	}
	if(ok){//数据校验通过
		$.ajax({
			url:path+"/user/add.do",
			data:{"name":name,"nick":nick,"password":password},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.status==1){
					$('#warning_1 span').html(result.msg);
					$('#warning_1').show();
				}
				if(result.status==0){
					alert(result.msg);
					$('#back').click();
				}
			},
			error:function(){
				alert("注册失败");
			}
		});
	}
}