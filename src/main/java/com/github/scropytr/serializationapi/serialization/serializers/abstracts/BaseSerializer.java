package com.github.scropytr.serializationapi.serialization.serializers.abstracts;

import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public abstract class BaseSerializer {

    private final ClassLoader classLoader = getClass().getClassLoader();

    public abstract <T> T load(Class<T> clazz, File file);
    public abstract <T> void save(T instance, File file);

    public void copyResource(File file) {
        try {
            InputStream resource = getResource(file.getName());
            if(resource == null) return;
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

    public InputStream getResource(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        try {
            URL url = classLoader.getResource(filename);

            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }
}
