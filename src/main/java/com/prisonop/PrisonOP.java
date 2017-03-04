package com.prisonop;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.prisonop.data.PlayerDataListener;
import com.prisonop.features.PlaytimeTrackingListener;
import com.prisonop.framework.FrameworkDisableListener;

public final class PrisonOP extends JavaPlugin {

	public static ConfigurationSection getConfig(String configName)
	{
		return JavaPlugin.getPlugin(PrisonOP.class).getConfig().getConfigurationSection(configName);
	}

	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();

		this.register(new FrameworkDisableListener());
		this.register(new PlayerDataListener());
		this.register(new PlaytimeTrackingListener());
	}

	private void register(Listener listener)
	{
		Bukkit.getPluginManager().registerEvents(listener, this);
	}

}