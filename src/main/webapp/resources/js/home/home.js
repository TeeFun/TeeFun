var socket = new SockJS(sockJSUrl);
stompClient = Stomp.over(socket);
stompClient.debug = null;
stompClient.connect({}, function(frame) {
	stompClient.subscribe("/topic/queueUpdated", function(data){
		alert("queueUpdated: " + data);
		refreshQueues();
	});
	stompClient.subscribe("/topic/queueCreated", function(data){
		alert("queueCreated: " + data);
		refreshQueues();
	});
	stompClient.subscribe("/topic/queueDeleted", function(data){
		alert("queueDeleted: " + data);
		refreshQueues();
	});
	stompClient.subscribe("/topic/gameReady", function(data){
		alert("gameReady: " + data);
	});
	stompClient.subscribe("/topic/gameStarted", function(data){
		alert("gameStarted: " + data);
	});
	stompClient.subscribe("/topic/gameAborted", function(data){
		alert("gameAborted: " + data);
	});
});

var refreshQueues = function () {
	$.get( "refreshQueues", function( data ) {
		$("#queues").html( data );
		$('[data-toggle="tooltip"]').tooltip()
	});
};

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

// Keep alive (keep the player active)
var inQueue = 0;
if (isInQueue) {
	inQueue = nbOfQueuesAtLoad;
}
setInterval(function(){
	if (inQueue > 0) {
		$.get("player/keepAlive");
	}
}, 5000);

var changeName = function() {
	var newName = $("#changeNameForm").find("input[name='nickname']").val();
	var posting = $.post("player/changeName?name=" + newName);

	posting.done(function() {
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
	var posting = $.post("queue/joinQueue?queueName=" + queueName);

	posting.done(function() {
		inQueue++;
		alert('Joined queue : ' + queueName);
	});
};

var quitQueue = function(queueName) {
	var posting = $.post("queue/quitQueue?queueName=" + queueName);

	posting.done(function() {
		inQueue--;
		alert('Quited queue : ' +  queueName);
	});
};

var quitAllQueues = function() {
	var posting = $.post("queue/quitAllQueues");

	posting.done(function() {
		inQueue = 0;
		alert('Left all queue');
	});
};

var askPassword = function(queueName) {
	var posting = $.post("queue/askPassword?queueName=" + queueName);

	posting.done(function(data) {
		alert("Password is : " + data);
	});
};

var playerReady = function(queueName, isReady) {
	var posting = $.post("queue/playerReady?queueName=" +queueName + "&isReady" + isReady);

	posting.done(function() {
	});
};

