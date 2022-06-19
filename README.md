# SerializationAPI

## Maven

[![](https://jitpack.io/v/ScropyTR/SerializationAPI.svg)](https://jitpack.io/#ScropyTR/SerializationAPI)

```pom.xml

<dependency>
    <groupId>com.github.ScropyTR</groupId>
    <artifactId>SerializationAPI</artifactId>
    <version>1.0.5</version>
</dependency>

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

# Usage

## Serialize to JSON
``` java
public class ExampleMain {

    private static Persist persist;

    public static void main(String[] args) {
        User user = new User("24357", "Scropy");

        persist = new Persist(new File("C:\\dataFolder"), Persist.PersistType.JSON);

        persist.save(user, "Config");
    }

}
```

## Serialize to YAML
``` java
public class ExampleMain {

    private static Persist persist;

    public static void main(String[] args) {
        User user = new User("24357", "Scropy");

        persist = new Persist(new File("C:\\dataFolder"), Persist.PersistType.YAML);

        persist.save(user, "Config");
    }

}
```

## Object

``` java
public class User {

    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

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
  "id": "24357",
  "name": "Scropy"
}

```

### User.yml (File)
``` yml
name: "24357"
id: "Scropy"

```
