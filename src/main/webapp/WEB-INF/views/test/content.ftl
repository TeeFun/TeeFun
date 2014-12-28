<div id="main" ng-app="teefun" ng-controller="mainController" >
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
								<input type="text" name="nickname" class="form-control" value="{{player.name}}">
								<span class="glyphicon glyphicon-ok form-control-feedback"></span>
							</div>
						</div>
						<div style="text-align: center;">
							<button class="btn btn-default" type="submit" ng-click="changeName()" onClick="return false;">Confirm</button>
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
			</div>
		</div>
	</div>

</div>