package com.github.scropytr.serializationapi.serialization.serializers.abstracts;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class BaseSerializer {

    private Plugin plugin;

    public BaseSerializer() { }

    public BaseSerializer(Plugin plugin) {
        this.plugin = plugin;
    }

    public abstract <T> T load(Class<T> clazz, File file);
    public abstract <T> void save(T instance, File file);

    public void copyResource(InputStream resource, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            int lenght;
            byte[] buf = new byte[1024];

            while ((lenght = resource.read(buf)) > 0) {
                out.write(buf, 0, lenght);
            }

            out.close();
            resource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }
}
