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

# Usage

## Serialize to JSON
``` java
public class ExamplePlugin {

    private Persist persist;

    private User user;

    @Override
    public void onEnable() {
        this.persist = new Persist(new File("C:\dataFolder"), Persist.PersistyType.JSON);

        this.user = this.persist.load(User.class, "User");
        this.persist.save(user, "User");
    }

}
```

## Serialize to YAML
``` java
public class ExamplePlugin {

    private Persist persist;

    private User user;

    @Override
    public void onEnable() {
        this.persist = new Persist(new File("C:\dataFolder"), Persist.PersistyType.YAML);

        this.user = this.persist.load(User.class, "User");
        this.persist.save(user, "User");
    }

}
```

## Object

``` java
public class User {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
}

```


### User.json (File)

``` json
{
  "id": "24357,
  "name": "Scropy"
}

```

### User.yml (File)
``` yml
name: "24357"
id: "Scropy"

```
