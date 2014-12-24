 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <!DOCTYPE html>
<html>

	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript">
			$.post("keepAlive.do");
			setInterval(function(){
   				$.post( "keepAlive.do");
			}, 5000);
			setTimeout(function(){
			   window.location.reload(1);
			}, 15000);
		</script>
	</head>

	<body>
		<form method=post action="changeName.do">
			<fieldset class="nickname">
				Nickname:
				<input type="text" name="nickname" value="nameless tee"/>
				<input type="submit" value="Confirm"/><br/>
			</fieldset>
		</form>

		<form method=post action="leaveAllQueues.do">
			<fieldset class="queues">
				<div>
					Currently in queues: ctf3<br/>
					<input type="submit" value="Leave every queue"/>
				</div>
			</fieldset>
		</form>

		<table class="main">
			<tbody>
				<tr>
					<td>
						<table class="queue">
						<c:set var="queueName" value="ctf1"/>
							<caption>${queueName} (3/10)</caption>
							<tbody>
							<c:forEach var="player" items="${players}">
								<c:if test='${player.isInQueue("${queueName}")}'>
								<tr><td>${player.name}</td></tr>
								</c:if>
							</c:forEach>
							<c:choose>
							<c:when test='${currentPlayer.isInQueue("${queueName}")}'>
								<tr><td>
									<form method=post action="leaveQueue.do">
										<input type="hidden" name="queue" value="${queueName}">
										<input type="submit" value="Leave"/>
									</form>
								</td></tr>
							</c:when>
							<c:otherwise>
								<tr><td>
									<form method=post action="joinQueue.do">
										<input type="hidden" name="queue" value="${queueName}">
										<input type="submit" value="Join"/>
									</form>
								</td></tr>
							</c:otherwise>
							</c:choose>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html> 