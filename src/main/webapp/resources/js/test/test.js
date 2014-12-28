// ----- AngularJS -----

var app = angular.module('teefun', []);
var data = {
		queues : {
			queue : {
				id : '1',
				name : 'name',
				state : "STATE",
				map : "map",
				gameType : "gt",
				scoreLimit : 3,
				timeLimit : 2,
				size : 10,
				maxSize : 10,
				players : {
					player : {
						id : '2',
						name : 'player'
					}
				}
			}
		},
		player : {
			name : 'nameless',
			id : 2
		}
};



app.factory('stompClient', function() {
	var socket = new SockJS(sockJSUrl);
	var stompClient = Stomp.over(socket);
	stompClient.debug = null;
	return stompClient;
})


app.controller('mainController', function($scope, stompClient) {
	
	$scope.queues = [];
	$scope.player = null;
	$scope.isInQueue = false;
	
	$.postjson("testJson", null, function(data) {
		$scope.queues = data.queues;
		$scope.player = data.player;
		updateButtons(data);
		$scope.$apply();
		// TODO finish loading
	});
	
	stompClient.connect({}, function(frame) {
		stompClient.subscribe("/topic/queueUpdated", function(data){
			console.log("Queue Updated: " + data);
			//var index = findQueue(data.id);
			//if(index != -1)
			//	$scope.queues[index] = data;
			$scope.$apply();
		});
		stompClient.subscribe("/topic/queueCreated", function(data){
			console.log("Queue Created: " + data);
			$scope.queues.push(data);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/queueDeleted", function(data){
			console.log("Queue Deleted: " + data);
			//var index = findQueue(data.id);
			//if(index != -1)
			//	$scope.queues.remove(index);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/gameReady", function(data){
			console.log("gameReady: " + data);
			//var readyQueueName = JSON.parse(data.body).name;
			//showReadyPanel(readyQueueName);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/gameStarted", function(data){
			console.log("gameStarted: " + data);
			//askPassword(JSON.parse(data.body).name);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/gameAborted", function(data){
			console.log("gameAborted: " + data);
		});
	});
	
	$scope.expandQueue = expandQueue;
	$scope.joinQueue = joinQueue;
	$scope.quitQueue = quitQueue;
	$scope.quitAllQueues = quitAllQueues;
});

// ---------------------

//Ask if the player really wants to leave if he is in queue
$(document).ready(function(){
	$(window).bind('beforeunload', function(){
		if (inQueue > 0) {
		  return "You are currently in a queue. Leaving the page will removing you from all queues. Are you sure you want to leave ?";
		}
	});
	$(window).bind('unload', function(){
		// Try to quit all queues on unload
		// TODO a little bit buggy. Need to interrupt request in order to use this or the client will be blocked on further connection
		// quitAllQueues();
	});
});

// ----- Bootstrap -----
var updateButtons = function(data) {
	var isInAnyQueue = false;
	for (i = 0; i < data.queues.length; i++) {
		var isInQueue = false;
		var queue = data.queues[i];
		for (j = 0; j < queue.players.length; j++) {
			var player = queue.players[j];
			if (player.id == data.player.id) {
				isInQueue = true;
				isInAnyQueue = true;
				break;
			}
		}
		updateQuitJoinButton(queue.id, isInQueue);
	}
	updateQuitAllQueuesButton(isInAnyQueue);
}

var showReadyPanel = function(queueName) {
	$('#gameReadyModal').modal("show");
}

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

var updateQuitJoinButton = function(queueId, isInQueue) {
	if (isInQueue) {
		$("#quit-"+queueId+"-button").show;
		$("#join-"+queueId+"-button").hide;
	} else {
		$("#quit-"+queueId+"-button").hide;
		$("#join-"+queueId+"-button").show;
	}
}

var updateQuitAllQueuesButton = function(isInQueue) {
	if (isInQueue) {
		$("#quitAllQueuesButton").enabled;
	} else {
		$("#quitAllQueuesButton").disabled;
	}
}

// ---------------------

// ----- Requests -----

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

// --------------------
