package dev.whatsappuser.minestom.lib.world;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.whatsappuser.minestom.lib.location.Location;
import lombok.Getter;
import marcono1234.gson.recordadapter.RecordTypeAdapterFactory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * development by TimoH created on 12:28:00 | 30.12.2022
 */

@Getter
public class WorldInfo {

    private final static Gson GSON = new GsonBuilder().registerTypeAdapterFactory(RecordTypeAdapterFactory.builder().allowDuplicateComponentValues().create()).create();

    private transient File file;

    private UUID id;
    private String name, generator;
    private List<Object> generatorArgs;
    private List<String> authors;
    private Location spawn;

    private WorldInfo() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAuthor(String name) {
        this.authors.add(name);
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public void setGenerator(String generator, List<Object> generatorArgs) {
        setGenerator(generator);
        this.generatorArgs = generatorArgs;
    }

    public Location spawn() {
        return spawn != null ? spawn : Location.ZERO.add(1);
    }

    public void setSpawn(Location spawn) {
        this.spawn = new Location(spawn);
    }

    public File directory() {
        return this.file == null ? null : this.file.getParentFile();
    }

    public void save() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter fw = new FileWriter(file)) {
                GSON.toJson(this, fw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WorldInfo of(@NotNull File file) {
        WorldInfo info = null;
        if (file.exists()) {
            try (FileReader fr = new FileReader(file)) {
                info = GSON.fromJson(fr, WorldInfo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (info == null) {
            info = new WorldInfo();
        }

        if (info.name == null) {
            info.name = file.getParentFile().getName();
        }

        if (info.id == null) {
            info.id = UUID.randomUUID();
        }

        info.file = file;
        return info;
    }
}
