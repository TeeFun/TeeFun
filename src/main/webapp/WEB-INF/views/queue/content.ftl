<!-- Admin -->
<div class="panel panel-info big">
	<div class="panel-body">
		<!-- Create queue -->
		<div class="panel panel-info">
			<div class="panel-heading">Create/edit a queue</div>
			<div class="panel-body">
				<form id="createQueueForm">
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Queue</span>
						<select class="form-control" id="createQueueId">
							<option value="-1">New Queue</option>
							<#list queues as queue>
								<option value="${queue.getId()}" onClick="fillCreateQueue(
									'${queue.getName()}',
									${queue.getMaxSize()},
									'${queue.getMap()}',
									'${queue.getGametype()}',
									${queue.getScoreLimit()},
									${queue.getTimeLimit()}
								);">${queue.getName()}</option>
							</#list>
						</select>
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Name</span>
						<input type="text" id="createQueueName" class="form-control"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Players</span>
						<input type="text" id="createQueueMaxSize" class="form-control"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Map</span>
						<input type="text" id="createQueueMap" class="form-control"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Gametype</span>
						<input type="text" id="createQueueGametype" class="form-control"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Score limit</span>
						<input type="text" id="createQueueScoreLimit" class="form-control"/>
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Time limit</span>
						<input type="text" id="createQueueTimeLimit" class="form-control"/>
					</div>
					<div class="checkbox">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="createQueuePermanent"/>Is queue permanent ?
							</label>
						</div>
					</div>
					<div style="text-align: center;">
						<button class="btn btn-default" type="submit" onClick="createQueue(); return false;">Create</button>
					</div>
				</form>
			</div>
		</div>

		<!-- Delete queue -->
		<div class="panel panel-info panel-last">
			<div class="panel-heading">Delete a queue</div>
			<div class="panel-body">
				<form id="deleteQueueForm">
					<div class="input-group" style="width: 200px; margin-left: auto; margin-right: auto;">
						<span class="input-group-addon">Queue</span>
						<select class="form-control" id="deleteQueueId">
							<#list queues as queue>
								<option value="${queue.getId()}">${queue.getName()}</option>
							</#list>
						</select>
					</div>
					<div style="text-align: center;">
						<button class="btn btn-default" type="submit" onClick="deleteQueue(); return false;">Delete</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>