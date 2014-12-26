<!-- SockJS -->
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<!-- Stomp -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script>
var isInQueue = ${isInQueue?c};
<#assign nbOfQueuesAtLoad = 0 />
<#list queues as queue>
	<#if queue.containsPlayer(currentPlayer)>
			<#assign nbOfQueuesAtLoad = nbOfQueuesAtLoad + 1 />
		</#if>
</#list>
var nbOfQueuesAtLoad = ${nbOfQueuesAtLoad};
</script>