
<!-- Nickname selection -->
<form id="changeNameForm">
	<fieldset class="nickname">
		Nickname:
		<input type="text" name="nickname" value="${currentPlayer.getName()}"/>
		<button type="button" class="btn btn-xs btn-primary" onClick="changeName()">Confirm</button><br/>
	</fieldset>
</form>

<!-- Queues list -->
<form id="quitAllQueuesForm">
	<fieldset class="queues">
		<div>
			Currently in queues: TODO<br/>
			<button type="button" class="btn btn-xs btn-danger" onClick="quitAllQueues()">Quit every queue</button>
		</div>
	</fieldset>
</form>

<form id="createQueueForm">
	<fieldset class="createQueue">
		<div>
			Queue Name:
			<input type="text" name="queueName" value="ctf2"/>
			<button type="button" class="btn btn-xs btn-primary" onClick="createQueue()">Create queue</button>
		</div>
	</fieldset>
</form>

<form id="deleteQueueForm">
	<fieldset class="deleteQueue">
		<div>
			Queue Name:
			<input type="text" name="queueName" value="ctf2"/>
			<button type="button" class="btn btn-xs btn-primary" onClick="deleteQueue()">Delete queue</button>
		</div>
	</fieldset>
</form>


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