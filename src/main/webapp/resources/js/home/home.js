// TODO refactor this
var readyQueueInfo = null;
var connected = false;

var socket = new SockJS(sockJSUrl);
stompClient = Stomp.over(socket);
stompClient.debug = null;
stompClient.connect({}, function(frame) {
	stompClient.subscribe("/topic/queueUpdated", function(data){
		console.log("queueUpdated: " + data);
		refreshQueues();
	});
	stompClient.subscribe("/topic/queueCreated", function(data){
		console.log("queueCreated: " + data);
		refreshQueues();
	});
	stompClient.subscribe("/topic/queueDeleted", function(data){
		console.log("queueDeleted: " + data);
		refreshQueues();
	});
	stompClient.subscribe("/topic/gameReady", function(data){
		console.log("gameReady: " + data);
		readyQueueInfo = JSON.parse(data.body);
		showReadyModal(readyQueueInfo);
	});
	stompClient.subscribe("/topic/gameStarted", function(data){
		console.log("gameStarted: " + data);
		askPassword(JSON.parse(data.body).id);
	});
	stompClient.subscribe("/topic/gameAborted", function(data){
		console.log("gameAborted: " + data);
	});
	connected = true;
});

var refreshQueues = function() {
	$.get("refreshQueues", function(data) {
		$("#queues").html(data);
		$("[data-toggle='tooltip']").tooltip()
	});
};

$(function() {
	$("[data-toggle='tooltip']").tooltip()
})

// Keep alive (keep the player active)
var inQueue = 0;
if(isInQueue) {
	inQueue = nbOfQueuesAtLoad;
	// Immedia keep alive 
	$.get("player/keepAlive");
}

setInterval(function() {
	if(inQueue > 0) {
		$.get("player/keepAlive");
	}
}, 5000);

// Ask if the player really wants to leave if he is in queue
$(document).ready(function(){
	$(window).bind("beforeunload", function(){
		if (inQueue > 0) {
		  return "You are currently in a queue. Leaving the page will removing you from all queues. Are you sure you want to leave ?";
		}
	});
	$(window).bind("unload", function(){
		// Try to quit all queues on unload
		// TODO a little bit buggy. Need to interrupt request in order to use this or the client will be blocked on further connection
		// quitAllQueues();
	});
});

var changeName = function() {
	if (!connected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var newName = $("#changeNameForm").find("input[name='nickname']").val();
	var posting = $.postjson("player/changeName", newName, function() {
		console.log("Changed name to : " + newName);
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

var joinQueue = function(queueId) {
	if (!connected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson("queue/joinQueue", queueId, function() {
		inQueue++;
		console.log("Joined queue: " + queueId);
	});
};

var quitQueue = function(queueId) {
	if (!connected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson("queue/quitQueue", queueId, function() {
		inQueue--;
		console.log("Quited queue: " + queueId);
	});
};

var quitAllQueues = function() {
	if (!connected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson("queue/quitAllQueues", null, function() {
		inQueue = 0;
		console.log("Left all queues");
	});
};

var askPassword = function(queueId) {
	if (!connected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson("queue/askPassword", queueId, function(data) {
		showStartedModal(data);
	});
};

var playerReady = function(isReady) {
	if (!connected) {
		alert("Please wait for websocket to connect");
		return;
	}
	$("#gameReadyModal").modal("hide");
	var input = {
			queueId : 		readyQueueInfo.id,
			isReady :		isReady
	};
	readyQueueInfo = null;
	var posting = $.postjson("queue/playerReady", input, function(data) {
		console.log("Player ready: " + isReady);
	});
};

var showReadyModal = function(queueInfo) {
	$("#gameReadyQueueName").text(queueInfo.name);
	$("#gameReadyProgressValue").text(queueInfo.size+"/"+queueInfo.maxSize);
	$("#gameReadyProgressBar").css("width", (100*queueInfo.size/queueInfo.maxSize)+"%");
	$("#gameReadyModal").modal("show");
}

var showStartedModal = function(info) {
	$("#gameStartedServerName").text(queueInfo.serverName);
	$("#gameStartedPassword").text(queueInfo.password);
	$("#gameStartedModal").modal("show");
}

function QueueController($scope, $http) {
    $scope.queues = [];
    $scope.addQueue = function(data) {
		$scope.queues.push(data);
    };
    $scope.findQueue = function(queueId) {
		for(var i = 0; i < arrayLength; i++) {
			if($scope.queues[i] == queueId)
				return i;
		}
		return -1;
    };
    $scope.removeQueue = function(queueId) {
		var index = findQueue(queueId);
		if(index != -1)
			$scope.queues.remove(index);
    };
    $scope.updateQueue = function(queueId, data) {
		var index = findQueue(queueId);
		if(index != -1)
			$scope.queues[index] = data;
    };

	$scope.addQueue(
		{
			id: 1,
			name: "Peter",
			map: "Jhons",
			gametype: "Jhons",
			scorelimit: "Jhons",
			timelimit: "Jhons",
			containsPlayer: true
		}
	);
}

var app = angular.module("myApp", []);
