<#include "/home/queue.ftl">

<div style="text-align: center; margin-top: 10px;">
	<#if isInQueue>
		<button class="btn btn-danger" type="button" onClick="quitAllQueues();">Quit all queues</button>
	<#else>
		<button class="btn btn-danger disabled" type="button" onClick="quitAllQueues();">Quit all queues</button>
	</#if>
</div>
