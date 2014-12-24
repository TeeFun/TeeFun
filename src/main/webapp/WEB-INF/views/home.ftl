<!DOCTYPE html>
<html>

	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="js/home.js"></script>
	</head>

	<body>
		<fieldset class="nickname">
			Nickname:
			<input type="text" name="nickname" value="nameless tee"/>
			<input type="button" value="Confirm" onClick="registerPlayer()" /><br/>
		</fieldset>

		<fieldset class="queues">
			<div>
				Currently in queues: ctf3<br/>
				<input type="button" value="Leave every queue" onClick="leaveQueues()" />
			</div>
		</fieldset>

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
	</body>
</html> 