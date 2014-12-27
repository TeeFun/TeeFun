/**
 *
 */
package com.teefun.model.json;

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
public class QueueData {

	@JsonProperty("name")
	private final String name;

	public QueueData(final Queue queue) {
		this.name = queue.getName();
	}

}
