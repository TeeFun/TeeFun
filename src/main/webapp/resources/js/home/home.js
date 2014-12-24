// Refresh content every X seconds
var refreshQueues = function () {
	$.get( "refreshQueues.do", function( data ) {
		$("#queues").html( data );
	});
};
setInterval(function(){
	refreshQueues();
}, 15000);

// Keep alive (keep the player active)
var inQueue = 0;
setInterval(function(){
	if (inQueue > 0) {
		$.get("player/keepAlive.do");
	}
}, 5000);

var changeName = function() {
	var newName = $("#changeNameForm").find("input[name='nickname']").val();
	var posting = $.post("player/changeName.do?name=" + newName);

	posting.done(function() {
		refreshQueues();
		alert('Name changed to ' + newName);
	});
};

var joinQueue = function(queueName) {
	var posting = $.post("queue/joinQueue.do?queueName=" + queueName);

	posting.done(function() {
		inQueue++;
		refreshQueues();
		alert('Joined queue : ' + queueName);
	});
};

var quitQueue = function(queueName) {
	var posting = $.post("queue/quitQueue.do?queueName=" + queueName);

	posting.done(function() {
		inQueue--;
		refreshQueues();
		alert('Quited queue : ' +  queueName);
	});
};

var quitAllQueues = function() {
	var posting = $.post("queue/quitAllQueues.do");

	posting.done(function() {
		inQueue = 0;
		refreshQueues();
		alert('Left all queue');
	});
};

var createQueue = function() {
	var queueName = $("#createQueueForm").find("input[name='queueName']").val();
	var queueMaxSize = $("#createQueueForm").find("input[name='queueMaxSize']").val();
	var posting = $.post("queue/createQueue.do?queueName=" + queueName + '&maxSize=' + queueMaxSize);

	posting.done(function() {
		refreshQueues();
		alert('Created queue : ' + queueName);
	});
};

var deleteQueue = function() {
	var queueName = $("#deleteQueueForm").find("input[name='queueName']").val();
	var posting = $.post("queue/deleteQueue.do?queueName=" + queueName );

	posting.done(function() {
		refreshQueues();
		alert('Deleted queue : ' + queueName);
	});
};
