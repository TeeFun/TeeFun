<table class="queue">
	<caption>
		${queue.getName()}
		<div class="progress">
			<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%; text-align: center;">
				<span>${queue.getSize()}/${queue.getMaxSize()}</span>
			</div>
		</div>
	</caption>
	<tbody>
	<#list queue.getPlayers() as player>
		<tr><td>${player.getName()}</td></tr>
	</#list>
	<#if queue.containsPlayer(currentPlayer)>
		<tr><td>
			<button type="button" class="btn btn-xs btn-primary" onClick="quitQueue('${queue.getName()}')">Leave</button>
		</td></tr>
	<#else>
		<tr><td>
			<button type="button" class="btn btn-xs btn-primary" onClick="joinQueue('${queue.getName()}')">Join</button>
		</td></tr>
	</#if>
	</tbody>
</table>
