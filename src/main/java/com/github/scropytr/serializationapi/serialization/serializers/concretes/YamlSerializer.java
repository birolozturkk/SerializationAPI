package com.github.scropytr.serializationapi.serialization.serializers.concretes;

import com.github.scropytr.serializationapi.serialization.serializers.abstracts.BaseSerializer;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class YamlSerializer extends BaseSerializer {

    private final Yaml yaml;

    public YamlSerializer() {

        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        yaml = new Yaml(options);
    }

    @Override
    public <T> T load(Class<T> clazz, File file) {

        try {
            if (!file.exists()) {
                file.createNewFile();
                copyResource(file);
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
                copyResource(file);
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            yaml.dump(instance, bufferedWriter);

            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
