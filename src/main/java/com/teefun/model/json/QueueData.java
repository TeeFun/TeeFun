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
 * QueueData in json format send to clients.
 *
 * @author Rajh
 *
 */
@JsonRootName("queue")
public class QueueData implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id.
	 */
	@JsonProperty
	private final Integer id;

	/**
	 * Name.
	 */
	@JsonProperty
	private final String name;

	/**
	 * State.
	 */
	@JsonProperty
	private final String state;

	/**
	 * Map.
	 */
	@JsonProperty
	private final String map;

	/**
	 * Game type.
	 */
	@JsonProperty
	private final String gameType;

	/**
	 * Score limit.
	 */
	@JsonProperty
	private final Integer scoreLimit;

	/**
	 * Time limit.
	 */
	@JsonProperty
	private final Integer timeLimit;

	/**
	 * Current size.
	 */
	@JsonProperty
	private final Integer size;

	/**
	 * Max size.
	 */
	@JsonProperty
	private final Integer maxSize;

	/**
	 * Players in queue.
	 */
	@JsonProperty
	private final List<PlayerData> players;

	/**
	 * Default constructor from a queue.
	 *
	 * @param queue the queue
	 */
	public QueueData(final Queue queue) {
		this.id = queue.getId();
		this.name = queue.getName();
		this.state = queue.getState().name();
		this.map = queue.getMap();
		this.gameType = queue.getGametype();
		this.scoreLimit = queue.getScoreLimit();
		this.timeLimit = queue.getTimeLimit();
		this.size = queue.getPlayers().size();
		this.maxSize = queue.getMaxSize();
		this.players = new ArrayList<PlayerData>();
		for (final Player player : queue.getPlayers()) {
			this.players.add(new PlayerData(player));
		}
	}

}
