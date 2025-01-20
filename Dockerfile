# <<Build stage>>
# Use Amazone Corretto 21 with Alpine as the base image for the build statge
FROM amazoncorretto:21-alpine AS build

# Set the working directory inside the container to /app
WORKDIR /app

# Copy all files the current directory to the /app directory in the container
COPY . .

# Rune the gradle wrapper to build the application JAR file without using the gradle  deemon
RUN ./gradlew bootJar --no-daemon

# <<Run stage>>
# Use Amazone Corretto 21 with Alpine as the base image for the runtime statge
FROM amazoncorretto:21-alpine

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the JAR file from the build stage to the runrime container as app.jar
COPY --from=build /app/build/libs/*.jar app.jar

# Define the command to run the application JAR file using java
ENTRYPOINT ["java", "-jar", "app.jar"]