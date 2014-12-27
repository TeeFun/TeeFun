// TODO refactor this
var readyQueueName;

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
		readyQueueName = JSON.parse(data.body).name;
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
		console.log('Name changed to ' + newName);
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
		console.log('Joined queue : ' + queueName);
	});
};

var quitQueue = function(queueName) {
	var posting = $.post("queue/quitQueue?queueName=" + queueName);

	posting.done(function() {
		inQueue--;
		console.log('Quited queue : ' +  queueName);
	});
};

var quitAllQueues = function() {
	var posting = $.post("queue/quitAllQueues");

	posting.done(function() {
		inQueue = 0;
		console.log('Left all queue');
	});
};

var askPassword = function(queueName) {
	var posting = $.post("queue/askPassword?queueName=" + queueName);

	posting.done(function(data) {
		console.log("Password is : " + data);
	});
};

var playerReady = function(isReady) {
	$('#gameReadyModal').modal('hide');
	var posting = $.post("queue/playerReady?queueName=" + readyQueueName + "&isReady=" + isReady);

	posting.done(function() {
	});
};

var showReadyPanel = function(queueName) {
	$('#gameReadyModal').modal('show');
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
