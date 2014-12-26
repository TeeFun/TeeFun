<!-- Admin -->
<div class="panel panel-info big">
	<div class="panel-body">
		<!-- Create queue -->
		<div class="panel panel-info">
			<div class="panel-heading">Create a queue</div>
			<div class="panel-body">
				<form id="createQueueForm">
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Name</span>
						<input type="text" id="createQueueName" class="form-control" placeholder="High-level ctf2">
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Players</span>
						<input type="text" id="createQueueMaxSize" class="form-control" placeholder="8">
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Map</span>
						<input type="text" id="createQueueMap" class="form-control" placeholder="ctf2">
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Gametype</span>
						<input type="text" id="createQueueGametype" class="form-control" placeholder="ctf">
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Score limit</span>
						<input type="text" id="createQueueScoreLimit" class="form-control" placeholder="600">
					</div>
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Time limit</span>
						<input type="text" id="createQueueTimeLimit" class="form-control" placeholder="0">
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
					<div class="input-group">
						<span class="input-group-addon" style="width: 100px;">Name</span>
						<input type="text" id="deleteQueueName" class="form-control" placeholder="ctf5">
					</div>
					<div style="text-align: center;">
						<button class="btn btn-default" type="submit" onClick="deleteQueue(); return false;">Delete</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>