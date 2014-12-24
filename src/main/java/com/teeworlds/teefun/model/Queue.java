package com.teeworlds.teefun.model;

import java.util.ArrayList;
import java.util.List;

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

	public Queue(final String name, final int maxSize) {
		this.name = name;
		this.maxSize = maxSize;
		this.players = new ArrayList<Player>();
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

}
