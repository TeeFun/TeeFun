var createQueue = function() {
	var queueName = $("#createQueueName").val();
	var input = {
			name : 			queueName,
			maxSize :		$("#createQueueMaxSize").val(),
			map :			$("#createQueueMap").val(),
			gametype :		$("#createQueueGametype").val(),
			scoreLimit :	$("#createQueueScoreLimit").val(),
			timeLimit :		$("#createQueueTimeLimit").val(),
			permanent : 	$("#createQueuePermanent").prop("checked"),
	};

	var posting = $.postjson("createQueue", input, function() {
		alert("Created queue: " + queueName);
	});
};

var deleteQueue = function() {
	var queueName = $("#deleteQueueId option:selected").text();
	var queueId = $("#deleteQueueId").val();
	var posting = $.postjson("deleteQueue", queueId, function() {
		alert("Deleted queue: " + queueName);
	});
};
