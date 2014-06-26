<%Integer uuid=(Integer)session.getAttribute("uuid"); %>
<%@include file="/include/header.jsp" %>
</head>
<body>
	<form action="/login" method="post">
		<input type="hidden" value="logout" name="op">
		<input type="submit" value="logout"/>
	</form>
	
	<input type="text" id="input_content"/>
	<input type="button" id="btn_throw" value="throw"/>
	<span id="result" style="color:red;" style="10px;"></span>
	
	<input type="button" id="btn_getBottle" value="GET"/>
	<div><span id="show_bottle"></span></div>
	<input type="hidden" value="" id="bid"/>
	<input type="hidden" value="<%=uuid %>" id="uuid"/>
	<input type="button" id="btn_openBottle" value="open"/>
	
	<input type="button" id="btn_test" value="test"/>
	
	<h1>Enter a message</h1>
    <form name="inputform">
        <input type="text" name="message" id="message" placeholder="Enter text to be sent" autofocus>
        <input type="submit" value="Send Web Socket Data">
    </form>
    <h2>Repsonse from Server</h2>
    <textarea id="responseText"></textarea>

<%@include file="/include/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	document.onkeydown = function (e) { 
		var theEvent = window.event || e; 
		var code = theEvent.keyCode || theEvent.which; 
		if (code == 13) { 
			$("#btn_throw").click(); 
		}
	}
	
	$("#btn_throw").click(function(){
		var content=$("#input_content").val();
		$.ajax({
			url:"/bottle",
			dataType:"json",
			type:"POST",
			data:{op:"throwBottle",content:content},
			success:function(json){
				if(json.code==0){
					$("#result").text("操作失败，请重试");
				}
				if(json.code==1){
					$("#result").text("操作成功");
				}
				if(json.code==2){
					$("#result").text("内容错误");
				}
			}
		});
	});
	
	$("#btn_getBottle").click(function(){
		$.ajax({
			url:"/bottle",
			dataType:"json",
			type:"POST",
			data:{op:"getBottle"},
			success:function(json){
				if(json.code==0){
					$("#result").text("没有瓶子");
				}
				if(json.code==1){
					$("#show_bottle").text(json.content);
					$("#bid").val(json.id);
				}
			}
		});
	});
	
	$("#btn_openBottle").click(function(){
		var bid=$("#bid").val();
		$.ajax({
			url:"/bottle",
			dataType:"json",
			type:"POST",
			data:{op:"openBottle",id:bid},
			success:function(json){
				if(json.code==0){
					$("#result").text("打开失败");
				}
				if(json.code==1){
					$("#show_bottle").text(json.content);
					$("#bid").val(json.id);
				}
			}
		});
	});
	
	$("#btn_test").click(new Sock().test);
});
</script>
</body>
</html>