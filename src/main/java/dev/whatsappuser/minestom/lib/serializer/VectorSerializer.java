package dev.whatsappuser.minestom.lib.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.whatsappuser.minestom.lib.location.Vector;
import dev.whatsappuser.minestom.lib.serializer.util.Serializer;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class VectorSerializer implements Serializer<Vector> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .create();

    public static final VectorSerializer INSTANCE = new VectorSerializer();

    @Override
    public String serialize(Vector attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public Vector deserialize(String dbData) {
        return gson.fromJson(dbData, Vector.class);
    }
}