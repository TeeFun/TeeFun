package com.teeworlds.teefun.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Player {

	private long lastKeepAlive = 0;
	private final List<String> queues = new ArrayList<String>();
	private String name = "Nameless Tee";

	public void keepAlive() {
		this.lastKeepAlive = System.currentTimeMillis();
	}

	public long getLastKeepAlive() {
		return this.lastKeepAlive;
	}

	public void addToQueue(final String queue) {
		this.keepAlive();
		if (this.queues.contains(queue)) {
			return;
		}
		this.queues.add(queue);
	}

	public void removeFromQueue(final String queue) {
		this.keepAlive();
		if (!this.queues.contains(queue)) {
			return;
		}
		this.queues.remove(queue);
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.keepAlive();
		this.name = name;
	}

	public void reset() {
		this.queues.clear();
		this.name = "Nameless Tee";
	}

	public boolean isInQueue(final String queue) {
		return this.queues.contains(queue);
	}

}
