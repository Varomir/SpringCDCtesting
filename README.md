# Spring CDC testing demo project

CDC (Consumer-Driven Contract) tests example with Spring microservices World.


#### Build, deploy and running pure Java application without container

For build without containers:

```mvn clean package```

Run all test (in parallel mode by default):

```mvn clean test```

For run DemoApp:

```java -Dspring.profiles.active=default -jar target/spring-cdc-testing-0.0.1-SNAPSHOT.war```

URL:
* http://localhost:8081/actuator
* http://localhost:8081/actuator/health
