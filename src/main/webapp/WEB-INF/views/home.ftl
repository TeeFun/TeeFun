<!DOCTYPE html>
<html>

	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="js/home.js"></script>

		<!-- Bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	</head>

	<body>
		<fieldset class="nickname">
			Nickname:
			<input type="text" name="nickname" value="nameless tee"/>
			<button type="button" class="btn btn-xs btn-danger" onClick="changeName()">Confirm</button><br/>
		</fieldset>

		<fieldset class="queues">
			<div>
				Currently in queues: ctf3<br/>
				<button type="button" onClick="leaveAllQueues()">Leave every queue</button>
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