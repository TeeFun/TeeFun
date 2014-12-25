var isInQueue = ${isInQueue?c};
<#assign nbOfQueuesAtLoad = 0 />
<#list queues as queue>
	<#if queue.containsPlayer(currentPlayer)>
			<#assign nbOfQueuesAtLoad = nbOfQueuesAtLoad + 1 />
		</#if>
</#list>
var nbOfQueuesAtLoad = ${nbOfQueuesAtLoad};
