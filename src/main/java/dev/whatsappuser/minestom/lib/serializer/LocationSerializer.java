package dev.whatsappuser.minestom.lib.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.whatsappuser.minestom.lib.location.Location;
import dev.whatsappuser.minestom.lib.serializer.util.Serializer;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class LocationSerializer implements Serializer<Location> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.builder().allowMissingComponentValues().create())
            .create();

    public static final LocationSerializer INSTANCE = new LocationSerializer();

    @Override
    public String serialize(Location attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Location deserialize(String dbData) {
        return gson.fromJson(dbData, Location.class);
    }
}