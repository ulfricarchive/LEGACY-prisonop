package com.prisonop.data;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerDataListener implements Listener {

	@EventHandler
	private void onLogin(AsyncPlayerPreLoginEvent event)
	{
		PlayerData.getPlayerData(event.getUniqueId());
	}

}