package com.github.scropytr.serializationapi;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class Persist {

    private ObjectMapper objectMapper;

    private File dataFolder;
    private PersistType persistType;

    public Persist(File dataFolder, PersistType persistType) throws IOException {
        if (!dataFolder.exists()) {
            dataFolder.createNewFile();
        }
        this.dataFolder = dataFolder;
        this.persistType = persistType;
        this.objectMapper = new ObjectMapper(persistType.getFactory()).configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
    }

    public <T> T load(Class<T> clazz, String filePath) {
        return load(clazz, new File(dataFolder, filePath + this.persistType.getExtension()));
    }

    public <T> T load(Class<T> clazz, File file) {
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, clazz);
            } catch (IOException e) {
                System.out.println("Failed to parse " + file + ": " + e.getMessage());
            }
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void save(T instance, File file) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, instance);
        } catch (IOException e) {
            System.out.println("Failed to save " + file.toString() + ": " + e.getMessage());
        }
    }

    public <T> void save(T instance, String filePath) {
        save(instance, new File(dataFolder, filePath + persistType.getExtension()));
    }

    public PersistType getPersistType() {
        return persistType;
    }

    public enum PersistType {

        YAML(".yml", new YAMLFactory()),
        JSON(".json", new JsonFactory());

        private final String extension;
        private final JsonFactory factory;

        PersistType(String extension, JsonFactory factory) {
            this.extension = extension;
            this.factory = factory;
        }

        public String getExtension() {
            return extension;
        }

        public JsonFactory getFactory() {
            return factory;
        }
    }

}
