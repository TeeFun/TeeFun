<!-- Forms -->
<div class="panel panel-default big">
	<div class="panel-body">
		<!-- Nickname selection -->
		<div class="panel panel-default">
			<div class="panel-heading">Select your nickname</div>
			<div class="panel-body" id="changeNameForm">
				<div class="input-group" style="width: 100%;">
					<div class="input-group has-success has-feedback">
						<span class="input-group-addon">Nickname</span>
						<input type="text" name="nickname" class="form-control" value="${currentPlayer.getName()}">
						<span class="glyphicon glyphicon-ok form-control-feedback"></span>
					</div>
				</div>
			</div>
		</div>

		<!-- Queues list -->
		<div class="panel panel-default panel-last">
			<div class="panel-heading">Queues you belong to</div>
			<div class="panel-body">
				<div id="queues">
					<#include "queues.ftl"/>
				</div>
				<div style="text-align: center; margin-top: 10px;">
					<#if currentPlayer.isInQueue()>
						<button class="btn btn-danger" type="button" onClick="quitAllQueues();">Quit all queues</button>
					<#else>
						<button class="btn btn-danger disabled" type="button" onClick="quitAllQueues();">Quit all queues</button>
					</#if>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Admin -->
<div class="panel panel-info big">
	<div class="panel-body">
		<!-- Create queue -->
		<div class="panel panel-info">
			<div class="panel-heading">Create a queue</div>
			<div class="panel-body" id="createQueueForm">
				<div class="input-group">
					<span class="input-group-addon" style="width: 100px;">Name</span>
					<input type="text" name="queueName" class="form-control" placeholder="ctf5">
				</div>
				<div class="input-group">
					<span class="input-group-addon" style="width: 100px;">Players</span>
					<input type="text" name="queueMaxSize" class="form-control" placeholder="8">
				</div>
				<div style="text-align: center;">
					<button class="btn btn-default" type="button" onClick="createQueue();">Create</button>
				</div>
			</div>
		</div>

		<!-- Delete queue -->
		<div class="panel panel-info panel-last">
			<div class="panel-heading">Delete a queue</div>
			<div class="panel-body" id="deleteQueueForm">
				<div class="input-group">
					<span class="input-group-addon" style="width: 100px;">Name</span>
					<input type="text" name="queueName" class="form-control" placeholder="ctf5">
				</div>
				<div style="text-align: center;">
					<button class="btn btn-default" type="button" onClick="deleteQueue();">Delete</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Highlights -->
<div class="panel panel-danger big">
	<div class="panel-body">
		<!-- Highlight box (status 0) -->
		<div class="panel panel-danger panel-last">
			<div class="panel-heading">Queue status</div>
			<div class="panel-body panel-highlight">
				Looking for match...<br/>
				Please wait for other players...
			</div>
		</div>
	</div>
</div>

<!-- Highlights -->
<div class="panel panel-success big">
	<div class="panel-body">
		<!-- Highlight box (status 1) -->
		<div class="panel panel-success panel-last">
			<div class="panel-heading">Queue status</div>
			<div class="panel-body panel-highlight">
				Match found for queue <span class="queue">ctf5</span>!<br/>
				Please accept or decline the game.<br/>
				<button type="button" class="btn btn-success">Accept</button>
				<button type="button" class="btn btn-danger">Decline</button><br/>
				<div class="progress" style="margin-top: 5px;">
					<span class="progress-value">Players ready: 8/10</span>
					<div class="progress-bar progress-bar-info progress-bar-striped" style="width: 80%;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
