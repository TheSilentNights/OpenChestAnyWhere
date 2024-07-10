package com.thesilentnights.openchestanywhere.config;

import com.thesilentnights.openchestanywhere.OpenChestAnywhere;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static final Map<String, Config> configurationMap;
    private final YamlConfiguration configAccess;

    public Config(String name, YamlConfiguration configAccess) {
        this.configAccess = configAccess;
        configurationMap.put(name, this);
    }

    static {
        configurationMap = new HashMap<>();
    }

    public static Config getConfig(String key) {
        return configurationMap.get(key);
    }

    public static void saveConfigs() {
        configurationMap.forEach((s, config) -> {
            try {
                config.configAccess.save(new File(OpenChestAnywhere.getPluginDataFolder() + "\\" + s + ".yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Boolean readBoolean(String key) {
        return this.configAccess.getBoolean(key);
    }

    public Integer readInt(String key){
        return this.configAccess.getInt(key);
    }


}
