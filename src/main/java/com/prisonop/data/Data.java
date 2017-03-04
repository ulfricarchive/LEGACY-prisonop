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

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.prisonop.PrisonOP;

public final class Data {

	private static final Map<UUID, Data> PERSISTENT_DATA = new HashMap<>();
	private static final ConfigurationSection CONFIG;
	private static final String FILE_EXTENSION;
	private static final Path FOLDER;

	static
	{
		CONFIG = PrisonOP.getConfig("data");
		FILE_EXTENSION = Data.CONFIG.getString("extension");
		FOLDER = Paths.get(Data.CONFIG.getString("path"));
		try
		{
			Files.createDirectories(Data.FOLDER);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Data getData(UUID uniqueId)
	{
		return Data.PERSISTENT_DATA.computeIfAbsent(uniqueId, key -> new Data(Data.FOLDER.resolve(uniqueId + Data.FILE_EXTENSION)));
	}

	static void saveAllData()
	{
		Data.PERSISTENT_DATA.values().forEach(Data::save);
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
			if (Files.notExists(path))
			{
				Files.createFile(path);
				return new YamlConfiguration();
			}
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