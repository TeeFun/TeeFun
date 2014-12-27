var createQueue = function() {
	var queueName =			$("#createQueueName").val();
	var queueMaxSize =		$("#createQueueMaxSize").val();
	var queueMap =			$("#createQueueMap").val();
	var queueGametype =		$("#createQueueGametype").val();
	var queueScoreLimit =	$("#createQueueScoreLimit").val();
	var queueTimeLimit =	$("#createQueueTimeLimit").val();
	var queuePermanent = 	$("#createQueuePermanent").prop('checked');
	var posting = $.post("createQueue?name=" + queueName + "&maxSize=" + queueMaxSize
							+ "&map=" + queueMap + "&gametype=" + queueGametype
							+ "&scoreLimit=" + queueScoreLimit + "&timeLimit=" + queueTimeLimit
							+ "&permanent=" + queuePermanent);

	posting.done(function() {
		console.log("Created queue: " + queueName);
	});
};

var deleteQueue = function() {
	var queueId = $("#deleteQueueId").val();
	var posting = $.post("deleteQueue?queueId=" + queueId);

	posting.done(function() {
		console.log("Deleted queue: " + queueId);
	});
};
