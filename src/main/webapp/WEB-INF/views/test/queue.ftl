<div class="input-group">
	<span class="input-group-btn" ng-if="isInQueue(queue)">
		<button id="quit-{{queue.id}}-button" class="btn btn-danger" type="button" ng-click="quitQueue(queue.id)">Quit</button>
	</span>
	<span class="input-group-btn" ng-if="!isInQueue(queue)">
		<button id="join-{{queue.id}}-button"  class="btn btn-success" type="button" ng-click="joinQueue(queue.id);">Join</button>
	</span>
	<span class="input-group-addon text-overflow queue-name" data-toggle="tooltip" data-placement="top" data-html="true" title="
		<table>
			<tr>
				<td style='text-align: right;'>State:&nbsp;</td>
				<td style='text-align: left;'>{{queue.state}}</td>
			</tr>
			<tr>
				<td style='text-align: right;'>Map:&nbsp;</td>
				<td style='text-align: left;'>{{queue.map}}</td>
			</tr>
			<tr>
				<td style='text-align: right;'>Gametype:&nbsp;</td>
				<td style='text-align: left;'>{{queue.gameType}}</td>
			</tr>
			<tr>
				<td style='text-align: right;'>Score limit:&nbsp;</td>
				<td style='text-align: left;'>{{queue.scoreLimit}}</td>
			</tr>
			<tr>
				<td style='text-align: right;'>Time limit:&nbsp;</td>
				<td style='text-align: left;'>{{queue.timeLimit}}</td>
			</tr>
		</table>
		">{{queue.name}}</span>
	<span class="input-group-addon queue-progress">
		<div class="progress">
			<span class="progress-value">{{queue.size}}/{{queue.maxSize}}</span>
			<div class="progress-bar progress-bar-info progress-bar-striped" style="width: {{(100*queue.size/queue.maxSize) | number:0}}%;"></div>
		</div>
	</span>
	<span class="input-group-btn">
		<button class="btn btn-primary" id="expand-{{queue.id}}-button" type="button" data-ng-click="expandQueue(queue.id);"><span class="caret"></span></button>
	</span>
</div>

<div class="panel panel-default" id="expand-{{queue.id}}-content" style="display: none;">
	<div class="panel-body">
		<table class="table table-bordered players">
			<tbody>
				<tr ng:repeat="queuePlayer in queue.players">
					<div ng-if="queuePlayer.id == player.id">
						<td class="user">{{queuePlayer.name}}</td>
					</div>
					<div ng-if="queuePlayer.id != player.id">
						<td>{{queuePlayer.name}}</td>
					</div>
				</tr>
			</tbody>
		</table>
	</div>
</div>


