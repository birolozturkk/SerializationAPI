package com.github.scropytr.serializationapi.serialization;

import com.github.scropytr.serializationapi.serialization.serializers.abstracts.BaseSerializer;
import com.github.scropytr.serializationapi.serialization.serializers.concretes.JsonSerializer;
import com.github.scropytr.serializationapi.serialization.serializers.concretes.YamlSerializer;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Persist {

    private Plugin plugin;

    @Getter
    private PersistyType persistyType;

    public Persist(Plugin plugin, PersistyType persistyType) {
        this.plugin = plugin;
        this.persistyType = persistyType;
        persistyType.getConfigurationService().setPlugin(plugin);
    }

    public <T> T load(Class<T> clazz, String filePath) {
        return load(clazz, new File(plugin.getDataFolder(), filePath + this.persistyType.getExtension()));
    }

    public <T> T load(Class<T> clazz, File file) {
        return this.persistyType.getConfigurationService().load(clazz, file);
    }

    public <T> void save(T instance, File file) {
        this.persistyType.getConfigurationService().save(instance, file);
    }

    public <T> void save(T instance, String filePath) {
        save(instance, new File(plugin.getDataFolder(), filePath + persistyType.getExtension()));
    }

    public enum PersistyType {

        YAML(".yaml", new YamlSerializer()),
        JSON(".json", new JsonSerializer());

        private String extension;
        private BaseSerializer baseSerializer;

        PersistyType(String extension, BaseSerializer baseSerializer) {
            this.extension = extension;
            this.baseSerializer = baseSerializer;
        }

        public String getExtension() {
            return extension;
        }

        public BaseSerializer getConfigurationService() {
            return baseSerializer;
        }


    }

}
