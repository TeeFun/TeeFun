package com.teeworlds.teefun.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * A player for matchmaking.
 *
 * @author Rajh
 *
 */
public class Player implements Serializable {

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Time in ms before the player becomes inactive.
	 */
	private static final int INACTIVE_TIME_LIMIT = 15000;

	/**
	 * Player name.
	 */
	private String name;

	/**
	 * Last time the player was active.
	 */
	private long lastActiveTime;

	/**
	 * Constructor.
	 */
	public Player(String name) {
		this.name = name;
		this.queues = new ArrayList<Queue>();
	}

	/**
	 * Keep alive packet in order to let the player active.
	 */
	public void keepAlive() {
		this.lastActiveTime = System.currentTimeMillis();
	}

	/**
	 * @return true if the player is still active
	 */
	public boolean isActive() {
		return System.currentTimeMillis() - this.lastActiveTime > INACTIVE_TIME_LIMIT;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(final String name) {
		this.keepAlive();
		this.name = name;
	}
}
