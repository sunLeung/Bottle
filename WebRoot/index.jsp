<%Integer uuid=(Integer)session.getAttribute("uuid"); %>
<%@include file="/include/header.jsp" %>
</head>
<body>
<input type="hidden" value="<%=uuid %>" id="uuid"/>
<div style="height:195px;width:100%;background:#0e83cd;position: fixed;"></div>

<div style="width:500px;height:700px;text-align: center;margin: 0 auto;">
	<div id="navi" class="hi-icon-wrap hi-icon-effect-5 hi-icon-effect-3a">
		<a link="throwTab" href="#" class="hi-icon hi-icon-pencil">throw</a>
		<a link="getTab" href="#" class="hi-icon hi-icon-earth">Contact</a>
		<a link="flistTab" href="#" class="hi-icon hi-icon-chat">Chat</a>
	</div>
	
	<!-- 扔瓶子 -->
	<div id="throwTab" class="popover bottom">
		<div class="arrow" style="left: 15%;"></div>
		<div><h3 style="float: left;">扔瓶子</h3><span style="float: right;position: relative;top: 35px;left: -45px;">还剩30个字</span></div>
		<textarea id="throw_content" style="width: 95%;height: 300px;resize: none;" rows="" cols=""></textarea>
		<div style="margin: 0 auto;height: 30px;width: 98%;border: 1px #ccc solid;">工具栏</div>
		<span id="throw_result" style="color:red;position: relative;top: 20px;left: 80px;"></span>
		<input type="button" id="btn_throw" class="btn btn-success btn-large" value="扔出去" style="position: relative;left: 108px;top: 18px;"/>
	</div>
	
	<!-- 捞瓶子 -->
	<div id="getTab" class="popover bottom">
		<div class="arrow" style="left: 50%;"></div>
		<div class="hi-icon-wrap hi-icon-effect-4 hi-icon-effect-4b" style="background: #0e83cd;width: 100%;height: 100%;">
			<a id="btn_get" href="#" class="hi-icon hi-icon-contract" style="margin-top: 140px;">GetBottle</a>
			<a id="btn_null" href="#" class="hi-icon hi-icon-none" style="margin-top: 140px;display: none;">没有瓶子</a>
			<a id="btn_open" href="#" class="hi-icon hi-icon-open" style="margin-top: 140px;display: none;">open</a>
		</div>
		<div class="preview">
			<button type="button" class="close btn_cancel" style="position: relative;top: -14px;left: 10px;">&times;</button>
			<div id="preview_content" style="width: 100%;height: 80%;border: 1px #fff solid;"></div>
			<input type="hidden" value="" id="preview_bid"/>
			<div class="btn-group" style="margin-top: 25px;width: 285px">
			  <a id="btn_cancel" href="#" class="btn btn-primary btn-large btn_cancel" style="width: 100px;">扔回去</a>
			  <a href="#" id="reply" class="btn btn-primary btn-large" style="width: 100px;">回复</a>
			</div>
		</div>
	</div>
	
	<!-- 瓶子列表 -->
	<div id="flistTab" class="popover bottom" style="display: block;">
		<div class="arrow" style="left: 86%;"></div>
		<table id="flist" class="table table-hover flist"></table>
	</div>
	
	<!-- 聊天界面 -->
	<div id="chatTab" class="chatTab">
		<button type="button" class="close" id="chatTabClose" data-dismiss="modal" style="position: relative;left:-15px;">&times;</button>
		<div id="chatWindow" class="well chatWindow"></div>
		<input type="hidden" id="toUuid"/>
		<div style="margin: 10px;"><input id="msgContent" type="text" class="input-medium search-query" style="width: 300px;margin-right: 13px;"/><button id="sendMsg" type="submit" class="btn">发送</button></div>
	</div>

</div>
	

<form action="/login" method="post">
	<input type="hidden" value="logout" name="op">
	<input type="submit" class="btn" value="logout"/>
</form>
	
<%@include file="/include/footer.jsp" %>
<script type="text/javascript" src="/js/socket.js"></script>
</body>
</html>