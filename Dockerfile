# Use the official maven image as the build image
FROM maven:3.8.3 AS build

# Set the working directory in the docker image
WORKDIR /app

# Copy the pom.xml file into our image
COPY pom.xml .

# This will download all the maven dependencies (useful for caching)
RUN mvn dependency:go-offline

# Now, copy the rest of the source code
COPY src /app/src

# Build the application
RUN mvn clean package

# Now, use the official OpenJDK image to run our application
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the built JAR from the build image into our run image
COPY --from=build /app/target/*.jar app.jar

# Expose the default port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]