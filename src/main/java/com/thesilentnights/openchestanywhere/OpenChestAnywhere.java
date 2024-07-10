package com.thesilentnights.openchestanywhere;

import com.thesilentnights.openchestanywhere.config.Config;
import com.thesilentnights.openchestanywhere.utils.ModuleLoader;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class OpenChestAnywhere extends JavaPlugin {
    private static File dataFolder;
    private static OpenChestAnywhere instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!getDataFolder().exists()) {
            saveDefaultConfig();
        }
        dataFolder = getDataFolder();
        instance = this;
        new Config("config", (YamlConfiguration) getConfig());
        ModuleLoader.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Config.saveConfigs();
    }


    public static File getPluginDataFolder() {
        return dataFolder;
    }

    public static OpenChestAnywhere getInstance() {
        return instance;
    }
}
