<%@include file="/include/header.jsp" %>
<style type="text/css">
	.loginForm {
		position:absolute;
		top:50%;
		left:50%;
		margin:-120px 0 0 -285px;
		width:570px;
		height:180px;
	}
</style>
</head>
<body>
	<div class="loginForm">
		<div class="form-horizontal">
		  <div class="control-group">
		    <label class="control-label" for="inputEmail">Email</label>
		    <div class="controls">
		      <input type="text" id="inputEmail" placeholder="Email"/>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="inputPassword">Password</label>
		    <div class="controls">
		      <input type="password" id="inputPassword" placeholder="Password"/>
		    </div>
		  </div>
		  <div class="control-group">
		    <div class="controls">
		      <label class="checkbox">
		        <input type="checkbox"/> Remember me
		      </label>
		      <button type="button" id="login" class="btn">Sign in</button>
		      <span id="result" style="color:red;" style="10px;"></span>
		    </div>
		  </div>
		</div>
	</div>

<%@include file="/include/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#inputEmail").focus();
	$("#login").click(function(){
		var userName=$("#inputEmail").val();
		var pwd=$("#inputPassword").val();
		$.ajax({
			url:"/login",
			dataType:"json",
			type:"POST",
			data:{op:'login',userName:userName,password:pwd},
			success:function(json){
				if(json.code==0){
					$("#result").text("密码错误");
				}
				if(json.code==1){
					location.href ="index.jsp";
				}
			}
		});
	});
	
	document.onkeydown = function (e) { 
		var theEvent = window.event || e; 
		var code = theEvent.keyCode || theEvent.which; 
		if (code == 13) { 
			$("#login").click(); 
		}
	}
});
</script>
</body>
</html>