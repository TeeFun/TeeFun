<script type="text/javascript">

	// Refresh content every X seconds
	var refreshQueues = function () {
	$.get( "refreshQueues.do", function( data ) {
			$("#queues").html( data );
		});
	};
	setTimeout(function(){
		refreshQueues();
	}, 15000);
	
	// Keep alive (keep the player active)
	var inQueue = 0;
	setInterval(function(){
		if (inQueue > 0) {
			$.post("keepAlive.do");
		}
	}, 5000);
	
	var changeName = function(newName) {
		var newName = $("#changeNameForm").find("input[name='s']").val();
		var posting = $.post("changeName.do", { newName: newName } );

		posting.done(function() {
		});
	};
	
	var joinQueue = function(queueName) {
		inQueue++;
	};
	
	var leaveQueue = function(queueName) {
		inQueue--;
	};
	
	var leaveAllQueues = function() {
		inQueue = 0;
	};
	
	var createQueue = function(queueName, size) {
	};
	
	var deleteQueue = function(queueName) {
	};
	
</script>