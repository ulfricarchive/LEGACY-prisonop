package com.prisonop.features;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.prisonop.data.Data;
import com.prisonop.util.meta.MetaUtils;

public class PlaytimeTrackingListener implements Listener {

	@EventHandler
	private void onJoin(PlayerJoinEvent event)
	{
		MetaUtils.set(event.getPlayer(), "playtime", Instant.now());
	}

	@EventHandler
	private void onQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		Data data = Data.getData(player.getUniqueId());
		data.computeLong("playtime", playtime -> playtime + this.getSessionTime(player));
	}

	private long getSessionTime(Player player)
	{
		return MetaUtils.get(player, "playtime", Instant.class).orElseGet(Instant::now).until(Instant.now(), ChronoUnit.MILLIS);
	}

}