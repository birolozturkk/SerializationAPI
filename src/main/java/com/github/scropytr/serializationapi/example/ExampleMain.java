package com.github.scropytr.serializationapi.example;

import com.github.scropytr.serializationapi.serialization.Persist;

import java.io.File;

public class ExampleMain {

    private static Persist persist;

    public static void main(String[] args) {
        User user = new User("24357", "Scropy");

        persist = new Persist(new File("C:\\dataFolder"), Persist.PersistyType.YAML);

        persist.save(user, "Config");
    }

}