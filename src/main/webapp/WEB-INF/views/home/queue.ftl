<table class="queue">
	<caption>${queue.getName()} (${queue.getSize()}/${queue.getMaxSize()})</caption>
	<tbody>
	<#list queue.getPlayers() as player>
		<tr><td>${player}</td></tr>
	</#list>
	<#if queue.contains(currentPlayer)>
		<tr><td>
			<form method=post action="removeQueue.do">
				<input type="hidden" name="queue" value="${queue.getName()}">
				<input type="button" value="Leave"/>
			</form>
		</td></tr>
	<#else>
		<tr><td>
			<form method=post action="joinQueue.do">
				<input type="hidden" name="queue" value="${queue.getName()}">
				<input type="button" value="Join"/>
			</form>
		</td></tr>
	</#if>
	</tbody>
</table>