package com.github.scropytr.serializationapi.example;

import com.github.scropytr.serializationapi.serialization.Persist;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class ExamplePlugin extends JavaPlugin {

    private Persist persistJson;
    private Persist persistYaml;

    private User user;

    @Override
    public void onEnable() {
        this.persistJson = new Persist(this, Persist.PersistyType.JSON);
        this.persistYaml = new Persist(this, Persist.PersistyType.YAML);

        this.user = this.persistJson.load(User.class, "User");
        this.user = this.persistYaml.load(User.class, "User");

        Bukkit.getPlayer(UUID.fromString(this.user.getUuid())).sendMessage("Successful");
    }

}
