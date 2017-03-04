package com.prisonop.util.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public enum EventUtils {

	;

	public static void call(Event event)
	{
		Bukkit.getPluginManager().callEvent(event);
	}

}