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
							<input type="text" name="nickname" class="form-control" value="${currentPlayer.getName()}">
							<span class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>
					</div>
					<div style="text-align: center;">
						<button class="btn btn-default" type="submit" onClick="changeName(); return false;">Confirm</button>
					</div>
				</form>
			</div>
		</div>

		<!-- Queues list -->
		<div class="panel panel-default panel-last">
			<div class="panel-heading">Available queues</div>
			<div class="panel-body">
				<div id="queues">
					<#include "queues.ftl"/>
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
<div class="modal fade" id="gameReadyModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">Queue status</h4>
			</div>
			<div class="modal-body">
				Match found for queue <span class="queue"></span>!<br/>
				Please accept or decline the game.<br/>
				<div class="progress" style="margin-top: 5px;">
					<span class="progress-value">Players ready: 8/10</span>
					<div class="progress-bar progress-bar-info progress-bar-striped" style="width: 80%;"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-success" onClick="playerReady(true)">Accept</button>
				<button type="button" data-dismiss="modal" class="btn btn-danger" onClick="playerReady(false)">Decline</button><br/>
			</div>
		</div>
	</div>
</div>			
