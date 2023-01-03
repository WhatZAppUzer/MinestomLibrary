package dev.whatsappuser.minestom.lib.serializer.util;

public interface Serializer<T> {

    T deserialize(String input);

    String serialize(T input);

}
