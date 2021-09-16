package com.github.scropytr.serializationapi.serialization.serializers.concretes;

import com.github.scropytr.serializationapi.serialization.serializers.abstracts.BaseSerializer;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class YamlSerializer extends BaseSerializer {

    private final Yaml yaml = new Yaml();

    public YamlSerializer() {
    }

    public YamlSerializer(Plugin plugin) {
        super(plugin);
    }

    @Override
    public <T> T load(Class<T> clazz, File file) {

        try {
            if (!file.exists()) {
                file.createNewFile();
                copyResource(getPlugin().getResource(file.getName()), file);
            }

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            return yaml.loadAs(bufferedReader, clazz);

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

            yaml.dump(instance, bufferedWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
