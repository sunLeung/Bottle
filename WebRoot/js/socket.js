$(document).ready(function(){
	var socket;
	if (!window.WebSocket) {
	    window.WebSocket = window.MozWebSocket;
	}

	if (window.WebSocket) {
	    socket = new WebSocket("ws://localhost:8088/websocket");
	    socket.onopen = onopen;
	    socket.onmessage = onmessage;
	    socket.onclose = onclose;
	} else {
	    alert("Your browser does not support Web Socket.");
	}

	function onopen(event) {
	    online();
	}
	function onclose(event) {
		alert("Web Socket closed");
	}

	function onmessage(event) {
		var json=JSON.parse(event.data);
		if(json.op == 17){
			//alert(json.msg);
		}else if(json.op == 18){
			updateChatWindow(json);
		}else if(json.op == 19){
			updateFList(json.flist);
		}
	}


	function send(data) {
	    if (window.WebSocket) {
			if (socket.readyState == WebSocket.OPEN) {
				var json=JSON.stringify(data);
			    socket.send(json);
			} else {
			    alert("The socket is not open.");
			}
	    }
	}
	
	function online() {
		var key= $.cookie('key');
		var uuid=$("#uuid").val();
		var data={'op':'0','uuid':uuid,'key':key};
	    send(data);
	}
	
	$("#reply").live("click",function(){
		var bid=$("#preview_bid").val();
		$.ajax({
			url:"/bottle",
			dataType:"json",
			type:"POST",
			data:{op:"openBottle",id:bid},
			success:function(json){
				if(json.code==0){
					alert("打开失败");
				}
				if(json.code==1){
					chatTabShow(json.uuid);
				}
			}
		});
	});
	
	function updateFList(json){
		$.each(json, function(i,val){      
			var element='<tr uuid="'+val.uuid+'"><td><div>'+val.name+'</div></td><td><div style="width: 300px;">'+val.content+'</div></td></tr>';
			$("#flist").append(element);
		});
	}
	
	$("tr[uuid]").live("click",function(){
		var name=$(this).find("div").get(0).innerText;
		var msg=$(this).find("div").get(1).innerText;
		var uuid=$(this).attr("uuid");
		chatTabShow(uuid);
	});
	
	$("#sendMsg").live("click",function(){
		var content=$("#msgContent").val();
		if(content==""){
			return;
		}
		var uuid=$("#uuid").val();
		var toUuid=$("#toUuid").val();
		var data={'op':'1','uuid':uuid,'to':toUuid,'content':content};
		send(data);
		updateChatWindow(data);
		$("#msgContent").val('');
	});
	
	function updateChatWindow(data){
		var uuid=$("#uuid").val();
		var msg;
		if(uuid==data.uuid){
			msg='<div class="alert alert-info myMsg">'+data.content+'</div>';
		}else{
			msg='<div class="alert alert-success yourMsg">'+data.content+'</div>';
		}
		$("#chatWindow").append(msg);
	}
	
	function chatTabShow(uuid){
		$("#toUuid").val(uuid);
		$("#chatWindow").empty();
		$("#msgContent").val('');
		$("#chatTab").show();
	}
	
	
	$("#navi a").live("click",function(){
		var link=$(this).attr("link");
		$("#throwTab").hide(200);
		$("#getTab").hide(200);
		$("#flistTab").hide(200);
		$("#chatTab").hide();
		$("#"+link).show(200);
	});
	
	$("#btn_throw").click(function(){
		var content=$("#throw_content").val();
		$.ajax({
			url:"/bottle",
			dataType:"json",
			type:"POST",
			data:{op:"throwBottle",content:content},
			success:function(json){
				if(json.code==0){
					$("#throw_result").text("操作失败，请重试");
				}
				if(json.code==1){
					$("#throw_result").text("操作成功");
				}
				if(json.code==2){
					$("#throw_result").text("内容错误");
				}
			}
		});
	});
	
	$("#btn_get").click(function(){
		$.ajax({
			url:"/bottle",
			dataType:"json",
			type:"POST",
			data:{op:"getBottle"},
			success:function(json){
				if(json.code==0){
					$("#btn_get").hide(200);
					$("#btn_null").show(200);
				}
				if(json.code==1){
					$("#btn_get").hide(200);
					$("#btn_open").show(200);
					$("#preview_content").text(json.content);
					$("#preview_bid").val(json.id);
				}
			}
		});
	});
	
	$("#btn_null").click(function(){
		$("#btn_null").hide(200);
		$("#btn_get").show(200);
	});
	
	$("#btn_open").click(function(){
		$("#btn_open").hide();
		$("#btn_get").show();
		$(".preview").show(200);
	});
	
	$(".btn_cancel").click(function(){
		$(".preview").hide(200);
	});
	
	$("#chatTabClose").click(function(){
		$("#chatTab").hide();
	});
	
});
