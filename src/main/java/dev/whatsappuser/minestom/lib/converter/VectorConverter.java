package dev.whatsappuser.minestom.lib.converter;

import dev.whatsappuser.minestom.lib.location.Vector;
import dev.whatsappuser.minestom.lib.serializer.VectorSerializer;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * development by TimoH created on 12:54:05 | 30.12.2022
 */

@Converter
@javax.persistence.Converter
public class VectorConverter implements AttributeConverter<Vector, String>, javax.persistence.AttributeConverter<Vector, String> {

    @Override
    public String convertToDatabaseColumn(Vector attribute) {
        return VectorSerializer.INSTANCE.serialize(attribute);
    }

    @Override
    public Vector convertToEntityAttribute(String dbData) {
        return VectorSerializer.INSTANCE.deserialize(dbData);
    }
}
