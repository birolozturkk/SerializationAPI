package com.github.scropytr.serializationapi.serialization.serializers.concretes;

import com.github.scropytr.serializationapi.serialization.serializers.abstracts.BaseSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.Plugin;

import java.io.*;

public class JsonSerializer extends BaseSerializer {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JsonSerializer() {
    }

    public JsonSerializer(Plugin plugin) {
        super(plugin);
    }

    public <T> T load(Class<T> clazz, File file) {

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(file.getName());
                copyResource(getPlugin().getResource(file.getName()), file);
            }

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            return gson.fromJson(bufferedReader, clazz);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> void save(T instance, File file) {

        try {
            if (!file.exists()) {
                file.createNewFile();
                copyResource(getPlugin().getResource(file.getName()), file);
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            gson.toJson(instance, bufferedWriter);
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
