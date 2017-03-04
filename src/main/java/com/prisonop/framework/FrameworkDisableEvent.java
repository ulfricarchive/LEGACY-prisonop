package com.prisonop.framework;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;

public class FrameworkDisableEvent extends ServerEvent {

	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList()
	{
		return FrameworkDisableEvent.HANDLERS;
	}

	@Override
	public HandlerList getHandlers()
	{
		return FrameworkDisableEvent.HANDLERS;
	}

}