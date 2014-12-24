<table class="queue">
	<caption>${queue.getName()} (${queue.getSize()}/${queue.getMaxSize()})</caption>
	<tbody>
	<#list queue.getPlayers() as player>
		<tr><td>${player}</td></tr>
	</#list>
	<#if queue.containsPlayer(currentPlayer)>
		<tr><td>
			<form method="post" action="quitQueue.do">
				<input type="hidden" name="queue" value="${queue.getName()}">
				<button type="button" class="btn btn-xs btn-primary">Leave</button>
			</form>
		</td></tr>
	<#else>
		<tr><td>
			<form method="post" action="joinQueue.do">
				<input type="hidden" name="queue" value="${queue.getName()}">
				<button type="button" class="btn btn-xs btn-primary">Join</button>
			</form>
		</td></tr>
	</#if>
	</tbody>
</table>