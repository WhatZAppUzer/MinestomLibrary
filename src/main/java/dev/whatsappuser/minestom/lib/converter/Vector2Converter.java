package dev.whatsappuser.minestom.lib.converter;

import dev.whatsappuser.minestom.lib.location.Vector2;
import dev.whatsappuser.minestom.lib.serializer.Vector2Serializer;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * development by TimoH created on 12:53:02 | 30.12.2022
 */

@Converter
@javax.persistence.Converter
public class Vector2Converter implements AttributeConverter<Vector2, String>, javax.persistence.AttributeConverter<Vector2, String> {
    @Override
    public String convertToDatabaseColumn(Vector2 vector2) {
        return Vector2Serializer.INSTANCE.serialize(vector2);
    }

    @Override
    public Vector2 convertToEntityAttribute(String s) {
        return Vector2Serializer.INSTANCE.deserialize(s);
    }
}
