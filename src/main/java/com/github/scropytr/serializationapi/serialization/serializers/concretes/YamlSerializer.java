package com.github.scropytr.serializationapi.serialization.serializers.concretes;

import com.github.scropytr.serializationapi.serialization.serializers.abstracts.BaseSerializer;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

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
                file.getParentFile().mkdir();
                file.createNewFile();
                copyResource(file);
            }

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            T result = yaml.loadAs(bufferedReader, clazz);
            bufferedReader.close();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> void save(T instance, File file) {

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                copyResource(file);
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            yaml.dump(instance, bufferedWriter);

            bufferedWriter.flush();
            bufferedWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
