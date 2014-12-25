package com.teeworlds.teefun.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.teeworlds.teefun.model.teeworlds.TeeworldsConfig;
import com.teeworlds.teefun.util.TeeworldsConfigUtil;

/**
 * A queue representation.
 *
 * @author Choupom
 *
 */
public class Queue {
	private final String name;
	private final int maxSize;
	private final List<Player> players;
	private final TeeworldsConfig serverConfig;

	public Queue(final String name, final int maxSize) {
		this.name = name;
		this.maxSize = maxSize;
		this.players = new ArrayList<Player>();
		this.serverConfig = TeeworldsConfigUtil.getDefaultConfig();
	}

	public String getName() {
		return this.name;
	}

	public int getMaxSize() {
		return this.maxSize;
	}

	public int getSize() {
		return this.players.size();
	}

	public void addPlayer(final Player player) {
		if (this.players.size() < this.maxSize && !this.players.contains(player)) {
			this.players.add(player);
		}
	}

	public void removePlayer(final Player player) {
		if (this.players.contains(player)) {
			this.players.remove(player);
		}
	}

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

}
