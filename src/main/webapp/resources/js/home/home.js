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
if (isInQueue) {
	inQueue = nbOfQueuesAtLoad;
}
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

var expandQueue = function(queueName) {
	var button = $("#expand-" + queueName + "-button");
	var content = $("#expand-" + queueName + "-content");

	if(content.css("display") == "block") {
		button.html("<span class=\"caret\"></span>");
		content.css("display", "none");
	}
	else {
		button.html("<span class=\"dropup\"><span class=\"caret\"></span></span>");
		content.css("display", "block");
	}
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
	var queueName = $("#createQueueName").val();
	var queueMaxSize = $("#createQueueMaxSize").val();
	var posting = $.post("queue/createQueue.do?queueName=" + queueName + '&maxSize=' + queueMaxSize);

	posting.done(function() {
		refreshQueues();
		alert('Created queue : ' + queueName);
	});
};

var deleteQueue = function() {
	var queueName = $("#deleteQueueName").val();
	var posting = $.post("queue/deleteQueue.do?queueName=" + queueName );

	posting.done(function() {
		refreshQueues();
		alert('Deleted queue : ' + queueName);
	});
};
