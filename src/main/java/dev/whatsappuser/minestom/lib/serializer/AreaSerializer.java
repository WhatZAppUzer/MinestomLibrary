package dev.whatsappuser.minestom.lib.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.whatsappuser.minestom.lib.area.Area;
import dev.whatsappuser.minestom.lib.area.CuboidArea;
import dev.whatsappuser.minestom.lib.area.PolyArea;
import dev.whatsappuser.minestom.lib.serializer.util.RuntimeTypeAdapterFactory;
import dev.whatsappuser.minestom.lib.serializer.util.Serializer;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;

public class AreaSerializer implements Serializer<Area> {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(RecordTypeAdapterFactory.DEFAULT)
            .registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Area.class)
                    .registerSubtype(CuboidArea.class, "cuboid")
                    .registerSubtype(PolyArea.class, "polygon"))
            .create();

    public final static AreaSerializer INSTANCE = new AreaSerializer();

    @Override
    public String serialize(Area attribute) {
        return gson.toJson(attribute, Area.class);
    }

    @Override
    public Area deserialize(String dbData) {
        return gson.fromJson(dbData, Area.class);
    }

}