package dev.whatsappuser.minestom.lib.converter;

import dev.whatsappuser.minestom.lib.location.Location;
import dev.whatsappuser.minestom.lib.serializer.LocationSerializer;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * development by TimoH created on 12:51:48 | 30.12.2022
 */

@Converter
@javax.persistence.Converter
public class LocationConverter implements AttributeConverter<Location, String>, javax.persistence.AttributeConverter<Location, String> {
    @Override
    public String convertToDatabaseColumn(Location location) {
        return LocationSerializer.INSTANCE.serialize(location);
    }

    @Override
    public Location convertToEntityAttribute(String s) {
        return LocationSerializer.INSTANCE.deserialize(s);
    }
}
