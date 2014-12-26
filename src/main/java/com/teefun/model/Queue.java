package com.teefun.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.teefun.model.teeworlds.TeeworldsConfig;
import com.teefun.util.TeeworldsConfigUtil;

/**
 * A queue representation.
 *
 * @author Choupom
 *
 */
public class Queue {

	/**
	 * Queue name.
	 */
	private final String name;

	/**
	 * Queue capacity.
	 */
	private final int maxSize;

	/**
	 * Map.
	 */
	private final String map;

	/**
	 * Game type.
	 */
	private final String gametype;

	/**
	 * Score limit.
	 */
	private final int scoreLimit;

	/**
	 * Time limit.
	 */
	private final int timeLimit;

	/**
	 * List of players which joined the queue.
	 */
	private final List<Player> players = new CopyOnWriteArrayList<Player>();

	/**
	 * Server config associated to this queue.
	 */
	private final TeeworldsConfig serverConfig;

	/**
	 * Default constructor.
	 *
	 * @param name queue name
	 * @param maxSize queue capacity
	 */
	public Queue(final String name, final int maxSize, final String map, final String gametype, final int scoreLimit, final int timeLimit) {
		this.name = name;
		this.maxSize = maxSize;
		this.map = map;
		this.gametype = gametype;
		this.scoreLimit = scoreLimit;
		this.timeLimit = timeLimit;
		this.serverConfig = this.makeConfig();
	}

	/**
	 * Make a config for this server.
	 */
	private TeeworldsConfig makeConfig() {
		final TeeworldsConfig config = TeeworldsConfigUtil.getDefaultConfig();
		config.setVariable("sv_map", this.map);
		config.setVariable("sv_max_clients", this.maxSize);
		config.setVariable("sv_scorelimit", this.scoreLimit);
		config.setVariable("sv_timelimit", this.timeLimit);
		config.setVariable("sv_gametype", this.gametype);
		return config;
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
		if (this.players.size() < this.maxSize && !this.players.contains(player)) {
			this.players.add(player);
		}
	}

	/**
	 * Remove a player from queue.
	 *
	 * @param player the player to be removed
	 */
	public void removePlayer(final Player player) {
		if (this.players.contains(player)) {
			this.players.remove(player);
		}
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

	/**
	 * @return the {@link #serverConfig}
	 */
	public TeeworldsConfig getServerConfig() {
		return this.serverConfig;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.name).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Queue) {
			final Queue other = (Queue) obj;
			return new EqualsBuilder().append(this.name, other.name).isEquals();
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

}
