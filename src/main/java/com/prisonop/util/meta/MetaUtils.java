package com.prisonop.util.meta;

import java.util.List;
import java.util.Optional;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;

import com.prisonop.PrisonOP;

public enum MetaUtils {

	;

	public static <T> Optional<T> get(Metadatable metadatable, String path, Class<T> type)
	{
		List<MetadataValue> metadata = metadatable.getMetadata("u_" + path);

		for (MetadataValue value : metadata)
		{
			Object data = value.value();

			if (type.isInstance(data))
			{
				@SuppressWarnings("unchecked")
				T casted = (T) data;
				return Optional.of(casted);
			}
		}

		return Optional.empty();
	}

	public static void set(Metadatable metadatable, String path, Object value)
	{
		metadatable.setMetadata("u_" + path, new FixedMetadataValue(PrisonOP.getInstance(), value));
	}

}