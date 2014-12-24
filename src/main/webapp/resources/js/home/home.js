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
	
	var changeName = function() {
		var newName = $("#changeNameForm").find("input[name='nickname']").val();
		var posting = $.post("changeName.do", { name: newName } );

		posting.done(function() {
		});
	};
	
	var joinQueue = function(queueName) {
		var queueName = $form.find("input[name='queueName']").val();
		var posting = $.post("joinQueue.do", { queueName: queueName } );

		posting.done(function() {
			inQueue++;
		});
	};
	
	var quitQueue = function(queueName) {
		var queueName = $form.find("input[name='queueName']").val();
		var posting = $.post("quitQueue.do", { queueName: queueName } );

		posting.done(function() {
			inQueue--;
		});
	};
	
	var leaveAllQueues = function() {
		var posting = $.post("quitAllQueues.do");

		posting.done(function() {
			inQueue = 0;
		});
	};
	
	var createQueue = function() {
		var queueName = $("#changeNameForm").find("input[name='queueName']").val();
		var queueMaxSize = $("#changeNameForm").find("input[name='queueMaxSize']").val();
		var posting = $.post("createQueue.do", { queueName: queueName, maxSize: queueMaxSize } );

		posting.done(function() {
		});
	};
	
	var deleteQueue = function() {
		var queueName = $("#changeNameForm").find("input[name='queueName']").val();
		var posting = $.post("deleteQueue.do", { queueName: queueName } );

		posting.done(function() {
		});
	};
	
</script>