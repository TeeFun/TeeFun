<script type="text/javascript">
	$.post("keepAlive.do");
	setInterval(function(){
		$.post( "keepAlive.do");
	}, 5000);
	setTimeout(function(){
	   window.location.reload(1);
	}, 15000);
</script>