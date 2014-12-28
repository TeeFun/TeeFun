/**
 *
 */
package com.teefun.model.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.teefun.model.Player;
import com.teefun.model.Queue;

/**
 * Application data for angular.
 *
 * @author Rajh
 *
 */
@JsonRootName("appData")
public class AppData implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Queues list.
	 */
	@JsonProperty
	private final List<QueueData> queues;

	/**
	 * Current player.
	 */
	@JsonProperty
	private final PlayerData player;

	/**
	 * Default constructor.
	 *
	 * @param queues the queues
	 * @param player the player
	 */
	public AppData(final List<Queue> queues, final Player player) {
		this.queues = new ArrayList<QueueData>();
		for (final Queue queue : queues) {
			this.queues.add(new QueueData(queue));
		}
		this.player = new PlayerData(player);
	}

}
