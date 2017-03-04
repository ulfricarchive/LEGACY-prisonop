package com.prisonop.framework;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

import com.prisonop.PrisonOP;
import com.prisonop.util.event.EventUtils;

public class FrameworkDisableListener implements Listener {

	@EventHandler
	private void onDisable(PluginDisableEvent event)
	{
		if (this.isFramework(event.getPlugin()))
		{
			EventUtils.call(new FrameworkDisableEvent());
		}
	}

	private boolean isFramework(Plugin plugin)
	{
		return plugin.getClass() == PrisonOP.class;
	}

}