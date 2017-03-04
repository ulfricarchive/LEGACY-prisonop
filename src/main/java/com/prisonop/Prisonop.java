package com.prisonop;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.prisonop.data.PlayerDataListener;

public final class Prisonop extends JavaPlugin {

	@Override
	public void onEnable()
	{
		this.register(new PlayerDataListener());
	}

	private void register(Listener listener)
	{
		Bukkit.getPluginManager().registerEvents(listener, this);
	}

}