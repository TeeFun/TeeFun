package com.teeworlds.teefun.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A player for matchmaking.
 *
 * @author Rajh
 *
 */
public class Player implements Serializable {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Player.class);

	/**
	 * SUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Time in ms before the player becomes inactive.
	 */
	private static final int INACTIVE_TIME_LIMIT = 11000;

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
	public Player(final String name) {
		this.name = name;
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
		LOGGER.trace(String.format("Player '%s' %d - %d", this.name, System.currentTimeMillis(), this.lastActiveTime));
		return System.currentTimeMillis() - this.lastActiveTime < INACTIVE_TIME_LIMIT;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.name).toHashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Player) {
			final Player other = (Player) obj;
			return new EqualsBuilder().append(this.name, other.name).isEquals();
		} else {
			return false;
		}
	}
}
