/**
 *
 */
package com.teefun.model.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.teefun.model.Queue;

/**
 * QueueData in json format send to clients.
 *
 * @author Rajh
 *
 */
@JsonRootName("QueueData")
public class QueueData implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Queue id.
	 */
	@JsonProperty
	private final Integer id;

	/**
	 * Queue name.
	 */
	@JsonProperty
	private final String name;

	/**
	 * Default constructor from a queue.
	 *
	 * @param queue the queue
	 */
	public QueueData(final Queue queue) {
		this.id = queue.getId();
		this.name = queue.getName();
	}

}
