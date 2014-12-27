/**
 *
 */
package com.teefun.service.websocket;

import com.teefun.model.Queue;

/**
 * WebSocket handler. Used to send message to clients.
 *
 * @author Rajh
 *
 */
public interface WebSocketHandler {

	/**
	 * A queue has been updated.
	 *
	 * @param queue the queue
	 */
	public void queueUpdated(Queue queue);

	/**
	 * A queue has been created.
	 *
	 * @param queue the queue
	 */
	public void queueCreated(Queue queue);

	/**
	 * A queue has been deleted.
	 *
	 * @param queue the queue
	 */
	public void queueDeleted(Queue queue);

	/**
	 * A queue is ready and waiting for players to be ready.
	 *
	 * @param queue the queue
	 */
	public void gameReady(Queue queue);

	/**
	 * A queue is started. Sending password to clients.
	 *
	 * @param queue the queue
	 */
	public void gameStarted(Queue queue);

	/**
	 * A queue has aborted.
	 *
	 * @param queue the queue
	 */
	public void gameAborted(Queue queue);

}
