package dev.whatsappuser.minestom.lib.configuration;

import com.google.gson.JsonElement;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * development by TimoH created on 22:59:59 | 03.01.2023
 */

public interface IJsonConfiguration extends Nameable {

    <T extends IJsonConfiguration> T append(String key, String value);

    <T extends IJsonConfiguration> T append(String key, Number value);

    <T extends IJsonConfiguration> T append(String key, Boolean value);

    <T extends IJsonConfiguration> T append(String key, JsonElement value);

    <T extends IJsonConfiguration> T append(String key, Object value);

    <T extends IJsonConfiguration> T remove(String key);

    Set<String> keys();

    String getString(String key);

    int getInt(String key);

    long getLong(String key);

    double getDouble(String key);

    boolean getBoolean(String key);

    float getFloat(String key);

    short getShort(String key);

    String convertToJson();

    boolean save(File backend);

    boolean save(String path);

    <T extends IJsonConfiguration> T getDocument(String key);
}
