package com.prisonop.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.Supplier;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class Data {

	private static final Map<UUID, Data> PLAYER_DATA = new HashMap<>();
	private static final Path FOLDER = Paths.get("playerdata");

	public static Data getData(UUID uniqueId)
	{
		return Data.PLAYER_DATA.computeIfAbsent(uniqueId, key -> new Data(Data.FOLDER.resolve(uniqueId + ".yml")));
	}

	static void saveAllData()
	{
		Data.PLAYER_DATA.values().forEach(Data::save);
	}

	private final Path path;
	private final FileConfiguration data;
	private boolean changed;

	Data(Path path)
	{
		this.path = path;
		this.data = this.loadData(path);
	}

	private FileConfiguration loadData(Path path)
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

	public void computeIfAbsent(String path, Supplier<Object> value)
	{
		if (this.data.get(path) == null)
		{
			this.set(path, value.get());
		}
	}

	public Object compute(String path, Function<Object, Object> computation)
	{
		Object newValue = computation.apply(this.get(path));
		this.set(path, newValue);
		return newValue;
	}

	public Object computeLong(String path, LongFunction<Object> computation)
	{
		Object newValue = computation.apply(this.getLong(path));
		this.set(path, newValue);
		return newValue;
	}

	public Object get(String path)
	{
		return this.data.get(path);
	}

	public long getLong(String path)
	{
		return this.data.getLong(path);
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