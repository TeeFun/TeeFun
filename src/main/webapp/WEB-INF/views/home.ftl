<!DOCTYPE html>
<html>

	<head>
		<title>Teeworlds Matchmaking System</title>

		<!-- JQuery -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

		<!-- Javascript -->
		<script src="js/home.js"></script>

		<!-- Bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

		<!-- CSS -->
		<style type="text/css">
			fieldset {
				width: 400px;
				margin-left: auto;
				margin-right: auto;
			}
			p {
				width: 400px;
				margin-left: auto;
				margin-right: auto;
				border: 1px solid #AA0000;
				padding: 5px;
			}
			table.main {
				margin-left: auto;
				margin-right: auto;
			}
			table.main > tbody > tr > td {
				padding: 10px;
			}
			table.queue {
				width: 200px;
				border: 1px solid black;
			}
			table.queue > caption {
				font-weight: bold;
				border: 1px solid black;
				border-bottom: none;
				text-align: center;
				padding: 0px;
				color: black;
			}
			table.queue > tbody > tr > td {
				text-align: center;
				width: 80px;
			}
			table.queue > tbody > tr > td.user {
				color: red;
			}
			span.queue {
				font-weight: bold;
			}
			div.progress {
				margin-left: auto;
				margin-right: auto;
				width: 120px;
				margin-bottom: 8px;
			}
		</style>
	</head>

	<body>
		<!-- Nickname selection -->
		<fieldset class="nickname">
			Nickname:
			<input type="text" name="nickname" value="${currentPlayer.getName()}"/>
			<button type="button" class="btn btn-xs btn-primary" onClick="changeName()">Confirm</button><br/>
		</fieldset>

		<!-- Queues list -->
		<fieldset class="queues">
			<div>
				Currently in queues: ctf3<br/>
				<button type="button" class="btn btn-xs btn-danger" onClick="quitAllQueues()">Quit every queue</button>
			</div>
		</fieldset>

		<!-- Highlight box (status 0) -->
		<p class="highlight">
			Looking for match...<br/>
			Please wait for other players...
		</p>

		<!-- Highlight box (status 1) -->
		<p class="highlight">
			Match found for queue <span class="queue">ctf5</span>!<br/>
			Please accept or decline the game.<br/>
			<tr><td><button type="button" class="btn btn-xs btn-success">Accept</button></td></tr>
			<tr><td><button type="button" class="btn btn-xs btn-danger">Decline</button></td></tr><br/>
			Players ready:
			<div class="progress">
				<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 42%">
					<span>6/12</span>
				</div>
			</div>
		</p>

		<!-- Highlight box (status 2) -->
		<p class="highlight">
			Match found for queue <span class="queue">ctf5</span>!<br/>
			You have accepted the game.<br/>
			<tr><td><button type="button" class="btn btn-xs btn-danger">Decline</button></td></tr><br/>
			Players ready:
			<div class="progress">
				<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 50%">
					<span>6/12</span>
				</div>
			</div>
		</p>

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
	</body>
</html> 