var wsConnected = false;
var pageLoaded = false;

// ----- AngularJS -----

var app = angular.module('teefun', []);

app.factory('stompClient', function() {
	var socket = new SockJS(sockJSUrl);
	var stompClient = Stomp.over(socket);
	stompClient.debug = null;
	return stompClient;
})


app.controller('mainController', function($scope, stompClient) {
	var readyQueueId = -1;
	
	$scope.queues = [];
	$scope.player = {};
	
	$.postjson("appData", null, function(data) {
		$scope.queues = data.queues;
		$scope.player = data.player;
		for (var i = 0; i < $scope.queues.length; i++) {
			$scope.setQueueTable($scope.queues[i]);
		}
		$scope.$apply();
		$("[data-toggle='tooltip']").tooltip();
		pageLoaded = true;
		loadingDone();
	});
	
	stompClient.connect({}, function(frame) {
		stompClient.subscribe("/topic/queueUpdated", function(data){
			var queue = JSON.parse(data.body);
			var index = findQueue($scope.queues, queue.id);
			if(index != -1) {
				var wasExpand = isExpand(queue.id);
				$scope.queues[index] = queue;
				$scope.setQueueTable(queue);
				$scope.$apply();
				$("[data-toggle='tooltip']").tooltip();
				if (wasExpand) {
					expandQueue(queue.id);
				}
			}
		});
		stompClient.subscribe("/topic/queueCreated", function(data){
			var queue = JSON.parse(data.body);
			$scope.queues.push(queue);
			$scope.setQueueTable(queue);
			$scope.$apply();
			$("[data-toggle='tooltip']").tooltip();
		});
		stompClient.subscribe("/topic/queueDeleted", function(data){
			var queue = JSON.parse(data.body);
			var index = findQueue($scope.queues, queue.id);
			if(index != -1) {
				$scope.queues.remove(index);
				$scope.$apply();
			}
		});
		stompClient.subscribe("/topic/gameReady", function(data){
			var queue = JSON.parse(data.body);
			readyQueueId = JSON.parse(data.body);
			if (isInQueue(queue, $scope.player)) {
				showReadyModal(queue);
			}
		});
		stompClient.subscribe("/topic/gameStarted", function(data){
			askPassword(JSON.parse(data.body).id);
		});
		stompClient.subscribe("/topic/gameAborted", function(data){
		});
		wsConnected = true;
		loadingDone();
	});
	
	$scope.expandQueue = expandQueue;
	
	$scope.changeName = function(newName) {
		changeName(newName);
	}
	
	$scope.joinQueue = function(queueId) {
		joinQueue(queueId);
	};

	$scope.quitQueue = function(queueId) {
		quitQueue(queueId);
	};

	$scope.quitAllQueues = function() {
		quitAllQueues();
	};
	
	$scope.isInQueue = function(queue) {
		return isInQueue(queue, $scope.player);
	};
	$scope.isInAnyQueue  = function() {
		return isInAnyQueue($scope.queues, $scope.player);
	};
	
	$scope.playerRead = function(ready) {
		if (readyQueueId == -1) {
			alert("No queue found.");
			return;
		}
		playerReady(queueId, ready);
	};
	
	$scope.setQueueTable = function(queue) {
		var qTable = {};
		var rows = [];
		var nbCols = 4;
		var nbRows = Math.floor((queue.maxSize+nbCols-1) / nbCols);
		for (var i = 0; i < nbRows; i++) {
			var cols = [];
			for (var j = 0; j < nbCols; j++) {
				var index = i*j+j;
				var cell = {
						name: null,
						cssclass: null,
				};
				if (index >= queue.players.length) {
					if (index >= queue.maxSize) {
						cell.cssclass = "disabled";
					}
				} else {
					if (queue.players[index].id == $scope.player.id) {
						cell.cssclass = "user";
					}
					cell.name = queue.players[index].name;
				}
				cols.push(cell);
			}
			rows.push(cols);
		}
		qTable.rows = rows;
		queue.table = qTable;
	};
	
	setInterval(function() {
		if (isInAnyQueue($scope.queues, $scope.player)) {
			$.get(contextPathUrl + "/player/keepAlive");
		}
	}, 5000);
	
	//Ask if the player really wants to leave if he is in queue
	$(document).ready(function(){
		$(window).bind('beforeunload', function(){
			if (isInAnyQueue($scope.queues, $scope.player)) {
			  return "You are currently in a queue. Leaving the page will removing you from all queues. Are you sure you want to leave ?";
			}
		});
		$(window).bind('unload', function(){
			// Try to quit all queues on unload
			// TODO a little bit buggy. Need to interrupt request in order to use this or the client will be blocked on further connection
			// quitAllQueues();
		});
	});
});

// ---------------------

// ----- Other -----
var loadingDone = function () {
	if (pageLoaded && wsConnected) {
		$("#loading").hide();
		$("#main").show();
	}
}

var findQueue = function(queues, queueId) {
	for(var i = 0; i < queues.length; i++) {
		if(queues[i].id == queueId)
			return i;
	}
	return -1;
};

var isInQueue = function(queue, player) {
	for (var i = 0; i < queue.players.length; i++) {
		var queuePlayer = queue.players[i];
		if (queuePlayer.id == player.id) {
			return true;
		}
	}
	return false;
};

var isInAnyQueue  = function(queues, player) {
	for (var i = 0; i < queues.length; i++) {
		var queue = queues[i];
		for (var j = 0; j < queue.players.length; j++) {
			var queuePlayer = queue.players[j];
			if (queuePlayer.id == player.id) {
				return true;
			}
		}
	}
	return false;
};

// -----------------

// ----- Bootstrap -----

var showReadyModal = function(queueInfo) {
	$("#gameReadyQueueName").text(queueInfo.name);
	$("#gameReadyProgressValue").text(queueInfo.size+"/"+queueInfo.maxSize);
	$("#gameReadyProgressBar").css("width", (100*queueInfo.size/queueInfo.maxSize)+"%");
	$("#gameReadyModal").modal("show");
}

var showStartedModal = function(info) {
	$("#gameStartedServerName").text(info.serverName);
	$("#gameStartedPassword").text(info.password);
	$("#gameStartedModal").modal("show");
}

var isExpand = function(queueId) {
	var content = $("#expand-" + queueId + "-content");

	if(content.css("display") == "block") {
		return true;
	} else {
		return false;
	}
}

var expandQueue = function(queueId) {
	var button = $("#expand-" + queueId + "-button");
	var content = $("#expand-" + queueId + "-content");

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

var changeName = function(newName) {
	if (!wsConnected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson(contextPathUrl + "/player/changeName", { name : newName }, function() {
		console.log("Changed name to : " + newName);
	});
};

var joinQueue = function(queueId) {
	if (!wsConnected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson(contextPathUrl + "/queue/joinQueue", queueId, function() {
		console.log("Joined queue: " + queueId);
	});
};

var quitQueue = function(queueId) {
	if (!wsConnected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson(contextPathUrl + "/queue/quitQueue", queueId, function() {
		console.log("Quited queue: " + queueId);
	});
};

var quitAllQueues = function() {
	if (!wsConnected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson(contextPathUrl + "/queue/quitAllQueues", null, function() {
		console.log("Left all queues");
	});
};

var askPassword = function(queueId) {
	if (!wsConnected) {
		alert("Please wait for websocket to connect");
		return;
	}
	var posting = $.postjson(contextPathUrl + "/queue/askPassword", queueId, function(data) {
		showStartedModal(data);
	});
};

var playerReady = function(queueId, isReady) {
	if (!wsConnected) {
		alert("Please wait for websocket to connect");
		return;
	}
	showReadyModal(false);
	var input = {
			queueId : 		queueId,
			isReady :		isReady
	};
	readyQueueInfo = null;
	var posting = $.postjson(contextPathUrl + "/queue/playerReady", input, function(data) {
		console.log("Player is : " + isReady);
	});
};

// --------------------