### Spring CDC testing demo project


### Build, deploy and running pure Java application without container

For build without containers:

```mvn clean package```

For run:
```java -Dspring.profiles.active=default -jar target/spring-cdc-testing-0.0.1-SNAPSHOT.war```

URL:

http://localhost:8080/actuator

http://localhost:8080/actuator/health
