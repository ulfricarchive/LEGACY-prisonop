package com.prisonop.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import com.prisonop.framework.FrameworkDisableEvent;

public class PlayerDataListener implements Listener {

	@EventHandler
	private void onLogin(AsyncPlayerPreLoginEvent event)
	{
		Data.getData(event.getUniqueId());
	}

	@EventHandler
	private void onDisable(FrameworkDisableEvent event)
	{
		for (Player player : Bukkit.getOnlinePlayers())
		{
			player.kickPlayer("server framework disable");
		}

		Data.saveAllData();
	}

}