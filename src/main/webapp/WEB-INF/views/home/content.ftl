<!-- Nickname selection -->
<form id="changeNameForm">
	<div class="normal">
		<table style="margin: auto;"><tbody>
			<tr>
				<td class="form-label">Nickname:&nbsp;</td>
				<td><input type="text" name="nickname" value="${currentPlayer.getName()}"/></td>
				<td><button type="submit" class="btn btn-xs btn-primary" onClick="changeName(); return false;">Confirm</button></td>
			</tr>
		</tbody></table>
	</div>
</form>

<!-- Queues list -->
<div class="normal">
	<table style="margin: auto; text-align: center;"><tbody>
		<tr>
			<td>Currently in queues: TODO</td>
		</tr>
		<tr>
			<td><button type="button" class="btn btn-xs btn-danger" onClick="quitAllQueues()">Quit every queue</button></td>
		</tr>
	</tbody></table>
</div>

<!-- Create queue -->
<form id="createQueueForm">
	<div class="normal">
		<table style="margin: auto;"><tbody>
			<tr>
				<td class="form-label">Name:&nbsp;</td>
				<td><input type="text" name="queueName" value=""/></td>
				<td rowspan="2"><button type="submit" class="btn btn-xs btn-primary" onClick="createQueue(); return false;">Create queue</button></td>
			</tr>
			<tr>
				<td class="form-label">Max Size:&nbsp;</td>
				<td><input type="text" name="queueMaxSize" value="8"/></td>
			</tr>
		</tbody></table>
	</div>
</form>

<!-- Delete queue -->
<div class="normal">
	<table style="margin: auto;"><tbody>
		<tr>
			<td class="form-label">Name:&nbsp;</td>
			<td><input type="text" name="queueName" value=""/></td>
			<td rowspan="2"><button type="button" class="btn btn-xs btn-primary" onClick="deleteQueue()">Delete queue</button></td>
		</tr>
	</tbody></table>
</div>

<!-- Highlight box (status 0) -->
<div class="normal highlight">
	Looking for match...<br/>
	Please wait for other players...
</div>

<!-- Highlight box (status 1) -->
<div class="normal highlight">
	Match found for queue <span class="queue">ctf5</span>!<br/>
	Please accept or decline the game.<br/>
	<tr><td><button type="button" class="btn btn-xs btn-success">Accept</button></td></tr>
	<tr><td><button type="button" class="btn btn-xs btn-danger">Decline</button></td></tr><br/>
	<table><tbody><tr style="height: 25px;">
		<td>Players ready:&nbsp;</td>
		<td>
			<div class="progress" style="margin: auto;">
				<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 42%">
					<span>5/12</span>
				</div>
			</div>
		</td>
	</tr></tbody></table>
</div>

<!-- Highlight box (status 2) -->
<div class="normal highlight">
	Match found for queue <span class="queue">ctf5</span>!<br/>
	You have accepted the game.<br/>
	<tr><td><button type="button" class="btn btn-xs btn-danger">Decline</button></td></tr><br/>
	<table><tbody><tr style="height: 25px;">
		<td>Players ready:&nbsp;</td>
		<td>
			<div class="progress" style="margin: auto;">
				<div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 50%">
					<span>6/12</span>
				</div>
			</div>
		</td>
	</tr></tbody></table>
</div>

<#include "queues.ftl"/>
