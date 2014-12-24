<!-- Detailed queues list -->
<table id="queues" class="main">
	<tbody>
		<tr>
			<td>
				<#list queues as queue>
					<#include "/home/queue.ftl">
				</#list>
			</td>
		</tr>
	</tbody>
</table>