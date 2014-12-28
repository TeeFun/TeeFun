<div id="loading" style="padding-top: 50px;box-sizing:content-box;">
	<div class="circle"></div>
	<div class="circle1"></div>
</div>
<div id="main" ng-app="teefun" ng-controller="mainController" style="display: none">
	<!-- Forms -->
	<div class="panel panel-default big">
		<div class="panel-body">
			<!-- Nickname selection -->
			<div class="panel panel-default">
				<div class="panel-heading">Select your nickname</div>
				<div class="panel-body">
					<form id="changeNameForm">
						<div class="input-group" style="width: 100%;">
							<div class="input-group has-success has-feedback">
								<span class="input-group-addon">Nickname</span>
								<input type="text" name="nickname" class="form-control" ng-model="player.name">
								<span class="glyphicon glyphicon-ok form-control-feedback"></span>
							</div>
						</div>
						<div style="text-align: center;">
							<button class="btn btn-default" type="submit" ng-click="changeName(player.name)" onClick="return false;">Confirm</button>
						</div>
					</form>
				</div>
			</div>
	
			<!-- Queues list -->
			<div class="panel panel-default panel-last">
				<div class="panel-heading">Available queues</div>
				<div class="panel-body">
					<div id="queues" ng:repeat="queue in queues">
						<#include "queue.ftl"/>
					</div>
				</div>
				<div style="text-align: center; margin-bottom: 10px;" ng-if="isInAnyQueue()">
					<button id="quitAllQueuesButton" class="btn btn-danger" type="button" ng-click="quitAllQueues()">Quit all queues</button>
				</div>
				<div style="text-align: center; margin-bottom: 10px;" ng-if="!isInAnyQueue()">
					<button id="quitAllQueuesButton" class="btn btn-danger disabled" type="button" ng-click="quitAllQueues()">Quit all queues</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Game Ready Modal -->
<div class="modal fade" id="gameReadyModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Match found</h4>
			</div>
			<div class="modal-body">
				Match found for queue <span class="queue" id="gameReadyQueueName"></span>!<br/>
				Please accept or decline the game.<br/>
				<div class="progress" style="margin-top: 5px;">
					<span class="progress-value" id="gameReadyProgressValue"></span>
					<div class="progress-bar progress-bar-info progress-bar-striped" id="gameReadyProgressBar"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-success" onClick="playerReady(true);">Accept</button>
				<button type="button" data-dismiss="modal" class="btn btn-danger" onClick="playerReady(false);">Decline</button><br/>
			</div>
		</div>
	</div>
</div>			

<!-- Game Started Modal -->
<div class="modal fade" id="gameStartedModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Match started</h4>
			</div>
			<div class="modal-body">
				Your match has started!<br/>
				Please look for server <span class="highlight" id="gameStartedServerName"></span> in Teeworlds.<br/>
				Password: <span class="highlight" id="gameStartedPassword"></span>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-success">Close</button>
			</div>
		</div>
	</div>
</div>
