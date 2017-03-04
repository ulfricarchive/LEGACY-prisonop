package com.prisonop.features;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.prisonop.data.PlayerData;

public class PlaytimeTrackingListener implements Listener {

	@EventHandler
	private void onQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		PlayerData data = PlayerData.getPlayerData(player.getUniqueId());

		data.computeLong("playtime", playtime -> playtime + player.getLastPlayed());
	}

}