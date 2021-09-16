# SerializationAPI

## Maven

[![](https://jitpack.io/v/ScropyTR/SerializationAPI.svg)](https://jitpack.io/#ScropyTR/SerializationAPI)

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>SerializationAPI</artifactId>
    <version>1.0.0</version>
</dependency>

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

## Usage

``` java
public class ExamplePlugin extends JavaPlugin {

    private Persist persist;

    private User user;

    @Override
    public void onEnable() {
        this.persist = new Persist(this, Persist.PersistyType.JSON);

        this.user = this.persist.load(User.class, "User");

        Bukkit.getPlayer(UUID.fromString(this.user.getUuid())).sendMessage("Successful");
    }

}
```


### Object

``` java
public class User {

    private String uuid;
    private String name;

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}

```


### User.json (File)

``` json
{
  "uuid": "d1236f63-130f-37c5-98ad-4a85eb4b421e",
  "name": "Scropy"
}

```

