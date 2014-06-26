<%Integer uuid=(Integer)session.getAttribute("uuid"); %>
<%@include file="/include/header.jsp" %>
</head>
<body>
<div id="parent" style="display: none;">
	<div id="child1" style="display: none">child1</div>
	<div id="child2" style="display: block;">child2</div>
	<a href="#" id="act">test</a>
</div>
<%@include file="/include/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#parent").show();
	$("#act").click(function(){
		$("#child2").hide();
		$("#child1").show();
	});
	
});
</script>
</body>
</html>