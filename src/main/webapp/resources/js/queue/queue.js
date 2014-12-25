var createQueue = function() {
	var queueName = $("#createQueueName").val();
	var queueMaxSize = $("#createQueueMaxSize").val();
	var posting = $.post("createQueue.do?queueName=" + queueName + '&maxSize=' + queueMaxSize);

	posting.done(function() {
		alert('Created queue : ' + queueName);
	});
};

var deleteQueue = function() {
	var queueName = $("#deleteQueueName").val();
	var posting = $.post("deleteQueue.do?queueName=" + queueName );

	posting.done(function() {
		alert('Deleted queue : ' + queueName);
	});
};