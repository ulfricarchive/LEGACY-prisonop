package com.prisonop.features;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.prisonop.data.Data;

public class PlaytimeTrackingListener implements Listener {

	@EventHandler
	private void onQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		Data data = Data.getData(player.getUniqueId());

		data.computeLong("playtime", playtime -> playtime + player.getLastPlayed());
	}

}