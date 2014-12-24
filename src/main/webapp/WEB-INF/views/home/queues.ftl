<!-- Detailed queues list -->
<table class="main">
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