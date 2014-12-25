<div class="input-group">
	<span class="input-group-btn">
		<#if queue.containsPlayer(currentPlayer)>
			<button class="btn btn-danger" type="button" onClick="quitQueue('${queue.getName()}');">Quit</button>
		<#else>
			<button class="btn btn-success" type="button" onClick="joinQueue('${queue.getName()}');">Join</button>
		</#if>
	</span>
	<span class="input-group-addon text-overflow queue-name">${queue.getName()}</span>
	<span class="input-group-addon queue-progress">
		<div class="progress">
			<span class="progress-value">${queue.getSize()}/${queue.getMaxSize()}</span>
			<div class="progress-bar progress-bar-info progress-bar-striped" style="width: ${100*queue.getSize()/queue.getMaxSize()}%;"></div>
		</div>
	</span>
	<span class="input-group-btn">
		<button class="btn btn-primary" id="expand-${queue.getName()}-button" type="button" onClick="expandQueue('${queue.getName()}');"><span class="caret"></span></button>
	</span>
</div>

<div class="panel panel-default" id="expand-${queue.getName()}-content" style="display: none;">
	<div class="panel-body">
		<table class="table table-bordered players">
			<tbody>
				<#assign cols = 4 />
				<!-- for each cell -->
				<#list 0..queue.getMaxSize() - 1 as i>
					<!-- row begin -->
					<#if i % cols == 0>
						<tr>
					</#if>

					<#if i gte queue.getSize()>
						<!-- empty cell -->
						<td class="disabled"></td>
					<#else>
						<!-- player cell -->
						<#assign cellPlayer = queue.getPlayers()[i]>
						<#if cellPlayer == currentPlayer>
							<td class="user">${cellPlayer.getName()}</td>
						<#else>
							<td>${cellPlayer.getName()}</td>
						</#if>
					</#if>

					<!-- row end -->
					<#if i % cols == cols-1>
						</tr>
					</#if>
				</#list>
			</tbody>
		</table>
	</div>
</div>
