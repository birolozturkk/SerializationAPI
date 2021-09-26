package com.github.scropytr.serializationapi.serialization;

import com.github.scropytr.serializationapi.serialization.serializers.abstracts.BaseSerializer;
import com.github.scropytr.serializationapi.serialization.serializers.concretes.JsonSerializer;
import com.github.scropytr.serializationapi.serialization.serializers.concretes.YamlSerializer;
import lombok.Getter;

import java.io.File;

public class Persist {

    private File dataFolder;

    @Getter
    private PersistyType persistyType;

    public Persist(File dataFolder, PersistyType persistyType) {
        this.dataFolder = dataFolder;
        this.persistyType = persistyType;
    }

    public <T> T load(Class<T> clazz, String filePath) {
        return load(clazz, new File(dataFolder, filePath + this.persistyType.getExtension()));
    }

    public <T> T load(Class<T> clazz, File file) {
        return this.persistyType.getConfigurationService().load(clazz, file);
    }

    public <T> void save(T instance, File file) {
        this.persistyType.getConfigurationService().save(instance, file);
    }

    public <T> void save(T instance, String filePath) {
        save(instance, new File(dataFolder, filePath + persistyType.getExtension()));
    }

    public void setDataFolder(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    public void setPersistyType(PersistyType persistyType) {
        this.persistyType = persistyType;
    }

    public enum PersistyType {

        YAML(".yml", new YamlSerializer()),
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
