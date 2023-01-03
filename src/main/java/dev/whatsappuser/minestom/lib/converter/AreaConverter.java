package dev.whatsappuser.minestom.lib.converter;

import dev.whatsappuser.minestom.lib.area.Area;
import dev.whatsappuser.minestom.lib.serializer.AreaSerializer;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * development by TimoH created on 12:49:08 | 30.12.2022
 */
@Converter
@javax.persistence.Converter
public class AreaConverter implements AttributeConverter<Area, String>, javax.persistence.AttributeConverter<Area, String> {
    @Override
    public String convertToDatabaseColumn(Area points) {
        return AreaSerializer.INSTANCE.serialize(points);
    }

    @Override
    public Area convertToEntityAttribute(String s) {
        return AreaSerializer.INSTANCE.deserialize(s);
    }
}
