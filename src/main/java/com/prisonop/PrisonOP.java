package com.prisonop;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.prisonop.data.PlayerDataListener;
import com.prisonop.framework.FrameworkDisableListener;

public final class PrisonOP extends JavaPlugin {

	@Override
	public void onEnable()
	{
		this.register(new FrameworkDisableListener());
		this.register(new PlayerDataListener());
	}

	private void register(Listener listener)
	{
		Bukkit.getPluginManager().registerEvents(listener, this);
	}

}