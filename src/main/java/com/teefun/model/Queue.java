package com.teefun.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.teefun.model.teeworlds.TeeworldsConfig;
import com.teefun.model.teeworlds.TeeworldsServer;
import com.teefun.util.TeeworldsConfigUtil;

/**
 * A queue representation.
 *
 * @author Choupom
 *
 */
public class Queue {

	/**
	 * Queue counter to generate id.
	 */
	private static final AtomicInteger QUEUE_COUNTER = new AtomicInteger();

	/**
	 * Queue id.
	 */
	private final Integer id;

	/**
	 * Queue name.
	 */
	private String name;

	/**
	 * Queue capacity.
	 */
	private int maxSize;

	/**
	 * Map.
	 */
	private String map;

	/**
	 * Game type.
	 */
	private String gametype;

	/**
	 * Score limit.
	 */
	private int scoreLimit;

	/**
	 * Time limit.
	 */
	private int timeLimit;

	/**
	 * Teeworlds server associated when the game is started.
	 */
	private TeeworldsServer server;

	/**
	 * Queue state.
	 */
	private QueueState state = QueueState.WAITING_PLAYERS;

	/**
	 * Is the queue permanent ?. If true it will automatically reset after the game as finised.
	 */
	private boolean permanent;

	/**
	 * List of players which joined the queue.
	 */
	private final List<Player> players = new CopyOnWriteArrayList<Player>();

	/**
	 * List of player ready.
	 */
	private final List<Player> playersReady = new ArrayList<Player>();

	/**
	 * List of player not ready.
	 */
	private final List<Player> playersNotReady = new ArrayList<Player>();

	/**
	 * Default constructor.
	 */
	public Queue() {
		this.id = QUEUE_COUNTER.getAndAdd(1);
	}

	/**
	 * Make a config for this server.
	 *
	 * @return the configuration
	 */
	public TeeworldsConfig makeConfig() {
		final TeeworldsConfig config = TeeworldsConfigUtil.getDefaultConfig();
		config.setVariable("sv_name", String.format("TeeFun #%d", this.id));
		config.setVariable("sv_map", this.map);
		config.setVariable("sv_max_clients", this.maxSize);
		config.setVariable("sv_scorelimit", this.scoreLimit);
		config.setVariable("sv_timelimit", this.timeLimit);
		config.setVariable("sv_gametype", this.gametype);
		return config;
	}

	/**
	 * @return the {@link #id}
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the {@link #maxSize}
	 */
	public int getMaxSize() {
		return this.maxSize;
	}

	/**
	 * @return the {@link #map}
	 */
	public String getMap() {
		return this.map;
	}

	/**
	 * @return the {@link #gametype}
	 */
	public String getGametype() {
		return this.gametype;
	}

	/**
	 * @return the {@link #scoreLimit}
	 */
	public int getScoreLimit() {
		return this.scoreLimit;
	}

	/**
	 * @return the {@link #timeLimit}
	 */
	public int getTimeLimit() {
		return this.timeLimit;
	}

	/**
	 * @return the current size
	 */
	public int getSize() {
		return this.players.size();
	}

	/**
	 * Add a player to queue.
	 *
	 * @param player the player to be added
	 */
	public void addPlayer(final Player player) {
		if (QueueState.WAITING_PLAYERS != this.state) {
			return;
		}
		if (this.players.size() < this.maxSize && !this.players.contains(player)) {
			this.players.add(player);
		}
	}

	/**
	 * Remove a player from queue.
	 *
	 * @param player the player to be removed
	 */
	public boolean removePlayer(final Player player) {
		if (QueueState.WAITING_PLAYERS != this.state) {
			return false;
		}
		return this.players.remove(player);
	}

	/**
	 * Is the player in this queue ?
	 *
	 * @param player the player
	 * @return true if the player is in this queue
	 */
	public boolean containsPlayer(final Player player) {
		return this.players.contains(player);
	}

	/**
	 * @return the {@link #players}
	 */
	public List<Player> getPlayers() {
		return this.players;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.name).append(this.id).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Queue) {
			final Queue other = (Queue) obj;
			return new EqualsBuilder().append(this.id, other.id).isEquals();
		} else {
			return false;
		}
	}

	/**
	 * @return true if the queue is full
	 */
	public boolean isFull() {
		return this.players.size() == this.maxSize;
	}

	/**
	 * @return the {@link #server}
	 */
	public TeeworldsServer getServer() {
		return this.server;
	}

	/**
	 * @param server the {@link #server} to set
	 */
	public void setServer(final TeeworldsServer server) {
		this.server = server;
	}

	/**
	 * @return the {@link #state}
	 */
	public QueueState getState() {
		return this.state;
	}

	/**
	 * @param queueState the {@link #state} to set
	 */
	public void setState(final QueueState queueState) {
		this.state = queueState;
	}

	/**
	 * @return the {@link #permanent}
	 */
	public boolean isPermanent() {
		return this.permanent;
	}

	/**
	 * @return the {@link #playersReady}
	 */
	public List<Player> getPlayersReady() {
		return this.playersReady;
	}

	/**
	 * @return the {@link #playersNotReady}
	 */
	public List<Player> getPlayersNotReady() {
		return this.playersNotReady;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param maxSize the {@link #maxSize} to set
	 */
	public void setMaxSize(final int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @param map the {@link #map} to set
	 */
	public void setMap(final String map) {
		this.map = map;
	}

	/**
	 * @param gametype the {@link #gametype} to set
	 */
	public void setGametype(final String gametype) {
		this.gametype = gametype;
	}

	/**
	 * @param scoreLimit the {@link #scoreLimit} to set
	 */
	public void setScoreLimit(final int scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	/**
	 * @param timeLimit the {@link #timeLimit} to set
	 */
	public void setTimeLimit(final int timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * @param permanent the {@link #permanent} to set
	 */
	public void setPermanent(final boolean permanent) {
		this.permanent = permanent;
	}

	/**
	 * Reset the queue to waiting player state.
	 */
	public void reset() {
		this.state = QueueState.WAITING_PLAYERS;
		// Shall we make sure the server is shutdown ?
		this.server = null;
		this.players.clear();
		this.playersNotReady.clear();
		this.playersReady.clear();
	}

	/**
	 * Set the player ready state.
	 *
	 * @param player the player
	 * @param isReady is the player ready
	 * @return true if applied
	 */
	public boolean setPlayerReady(final Player player, final Boolean isReady) {
		if (!this.containsPlayer(player)) {
			return false;
		}

		if (this.playersReady.contains(player) || this.playersNotReady.contains(player)) {
			return false;
		}

		if (isReady) {
			this.playersReady.add(player);
		} else {
			this.playersNotReady.add(player);
		}
		return true;
	}

	/**
	 * @return true if every players in queue has respond to ready question
	 */
	public boolean hasEveryResponse() {
		return this.playersReady.size() + this.playersNotReady.size() == this.players.size();
	}

	/**
	 * @return true if every players are ready
	 */
	public boolean isEveryoneReady() {
		return this.playersReady.size() == this.players.size();
	}

	/**
	 * Remove leavers from queue.
	 */
	public void removeLeavers() {
		for (final Player player : this.players) {
			if (!this.playersReady.contains(player)) {
				this.players.remove(player);
			}
		}
		this.playersNotReady.clear();
		this.playersReady.clear();
	}

}
