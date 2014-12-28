// ----- AngularJS -----

var app = angular.module('teefun', []);

app.factory('stompClient', function() {
	var socket = new SockJS(sockJSUrl);
	var stompClient = Stomp.over(socket);
	stompClient.debug = null;
	return stompClient;
})


app.controller('mainController', function($scope, stompClient) {
	
	$scope.queues = [];
	$scope.player = {};
	
	$.postjson("testJson", null, function(data) {
		$scope.queues = data.queues;
		$scope.player = data.player;
		$scope.$apply();
		// TODO finish loading
	});
	
	stompClient.connect({}, function(frame) {
		stompClient.subscribe("/topic/queueUpdated", function(data){
			var queue = data.body;
			console.log("Queue Updated: " + queue);
			var index = findQueue(queue.id);
			if(index != -1)
				$scope.queues[index] = queue;
			$scope.$apply();
		});
		stompClient.subscribe("/topic/queueCreated", function(data){
			var queue = data.body;
			console.log("Queue Created: " + queue);
			$scope.queues.push(queue);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/queueDeleted", function(data){
			var queue = data.body;
			console.log("Queue Deleted: " + queue);
			var index = findQueue(queue.id);
			if(index != -1)
				$scope.queues.remove(index);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/gameReady", function(data){
			console.log("gameReady: " + data);
			var readyQueueName = JSON.parse(data.body).name;
			showReadyPanel(readyQueueName);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/gameStarted", function(data){
			console.log("gameStarted: " + data);
			askPassword(JSON.parse(data.body).name);
			$scope.$apply();
		});
		stompClient.subscribe("/topic/gameAborted", function(data){
			console.log("gameAborted: " + data);
		});
	});
	
	$scope.expandQueue = expandQueue;
	
	$scope.changeName = function(player) {
		stompClient.send("/app/changeName", {}, player.name);
	}
	
	$scope.joinQueue = function(queueId) {
		stompClient.send("/app/joinQueue", {}, queueId);
	};

	$scope.quitQueue = function(queueId) {
		stompClient.send("/app/quitQueue", {}, queueId);
	};

	$scope.quitAllQueues = function() {
		stompClient.send("/app/quitAllQueues");
	};
	
	$scope.isInQueue = function(queue) {
		return isInQueue(queue, $scope.player);
	};
	$scope.isInAnyQueue  = function() {
		return isInAnyQueue($scope.queues, $scope.player);
	}
});

// ---------------------

// ----- Other -----
var isInQueue = function(queue, player) {
	for (i = 0; i < queue.players.length; i++) {
		var queuePlayer = queue.players[i];
		if (queuePlayer.id == player.id) {
			return true;
		}
	}
	return false;
};

var isInAnyQueue  = function(queues, player) {
	for (i = 0; i < queues.length; i++) {
		var queue = queues[i];
		for (j = 0; j < queue.players.length; j++) {
			var queuePlayer = queue.players[j];
			if (queuePlayer.id == player.id) {
				return true;
			}
		}
	}
	return false;
};

// -----------------

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

// ---------------------

// ----- Requests -----

var joinQueue = function(stompClient, queueId) {
	stompClient.send("/topic/joinQueue", {}, queueId);
};

var quitQueue = function(stompClient, queueId) {
	stompClient.send("/topic/quitQueue", {}, queueId);
};

var quitAllQueues = function(stompClient) {
	stompClient.send("/topic/quitAllQueues");
};

// --------------------
