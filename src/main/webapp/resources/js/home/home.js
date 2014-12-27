// TODO refactor this
var readyQueueId;

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
		var readyQueueName = JSON.parse(data.body).name;
		showReadyPanel(readyQueueName);
	});
	stompClient.subscribe("/topic/gameStarted", function(data){
		console.log("gameStarted: " + data);
		askPassword(JSON.parse(data.body).name);
	});
	stompClient.subscribe("/topic/gameAborted", function(data){
		console.log("gameAborted: " + data);
	});
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
if(isInQueue)
	inQueue = nbOfQueuesAtLoad;
setInterval(function() {
	if(inQueue > 0) {
		$.get("player/keepAlive");
	}
}, 5000);

var changeName = function() {
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
	var posting = $.postjson("player/joinQueue", queueId, function() {
		inQueue++;
		console.log("Joined queue: " + queueId);
	});
};

var quitQueue = function(queueId) {
	var posting = $.postjson("player/quitQueue", queueId, function() {
		inQueue--;
		console.log("Quited queue: " + queueId);
	});
};

var quitAllQueues = function() {
	var posting = $.postjson("player/quitAllQueues", null, function() {
		inQueue = 0;
		console.log("Left all queues");
	});
};

var askPassword = function(queueId) {
	var posting = $.postjson("player/askPassword", queueId, function(data) {
		alert("The password is : '" + data +"'");
	});
};

var playerReady = function(isReady) {
	$('#gameReadyModal').modal("hide");
	var input = {
			queueId : 		readyQueueId,
			isReady :		isReady
	};
	var posting = $.postjson("player/playerReady", input, function(data) {
		console.log("Player is : " + isReady);
	});
};

var showReadyPanel = function(queueName) {
	$('#gameReadyModal').modal("show");
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

var app = angular.module('myApp', []);
