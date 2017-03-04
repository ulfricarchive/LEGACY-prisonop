package com.prisonop.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class PlayerData {

	private static final Map<UUID, PlayerData> PLAYER_DATA = new HashMap<>();
	private static final Path FOLDER = Paths.get("playerdata");

	public static PlayerData getPlayerData(UUID uniqueId)
	{
		return PlayerData.PLAYER_DATA.computeIfAbsent(uniqueId, key -> new PlayerData(PlayerData.FOLDER.resolve(uniqueId + ".yml")));
	}

	private final Path path;
	private final FileConfiguration data;
	private boolean changed;

	PlayerData(Path path)
	{
		this.path = path;
		this.data = this.getData(path);
	}

	private FileConfiguration getData(Path path)
	{
		try
		{
			return YamlConfiguration.loadConfiguration(Files.newBufferedReader(path));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new YamlConfiguration();
		}
	}

	public void set(String path, Object value)
	{
		this.changed = true;
		this.data.set(path, value);
	}

	public void setIfEmpty(String path, Supplier<Object> value)
	{
		if (this.data.get(path) == null)
		{
			this.set(path, value.get());
		}
	}

	public void save()
	{
		if (this.changed)
		{
			try
			{
				this.data.save(path.toFile());
				this.changed = false;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}