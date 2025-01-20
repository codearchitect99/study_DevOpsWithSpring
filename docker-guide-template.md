# Docker guide template

```yaml
# docker-compose.yml
services:
  [DB_service name]:
    image: [dbms]:[dbms-version]
    container_name: mariadb
    ports:
      - "[local-port]:[container-port]"
    environment:
      - MYSQL_ROOT_PASSWORD=[db-username-password]
      - MYSQL_DATABASE=[database-name]
    volumes:
      - [db-volume-name]:/var/lib/mysql

  server:
    build:
      context: .
      dockerfile: Dockerfile
      platforms:
        - linux/amd64
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:[dbms]://[DB_service name]:[local-port]/[database-name]
      - SPRING_DATASOURCE_USERNAME=[db-username]
      - SPRING_DATASOURCE_PASSWORD=[db-username-password]
    depends_on:
      - [DB_service name]

volumes:
  [db-volume-name]:
```

```dockerfile
# Dockerfile

# <<Build stage>>
FROM amazoncorretto:21-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

# <<Run stage>>
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```yaml
# application-db.yml
spring:
  datasource:
    url: jdbc:[dbms]://localhost:[local-port]/[database-name]
    username: [db-username]
    password: [db-username-password]
    driver-class-name: org.mariadb.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
```
