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

	@JsonProperty("name")
	private final String name;

	public QueueData(final Queue queue) {
		this.name = queue.getName();
	}

}
