#FROM ubuntu:latest
#LABEL authors="mukeshpatil"
#
#ENTRYPOINT ["top", "-b"]

# Use an OpenJDK 11 image as base
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/sudoku-microservice-1.0-jar-with-dependencies.jar /app/

# Specify the command to run the application when the container starts
CMD ["java", "-jar", "sudoku-microservice-1.0-jar-with-dependencies.jar"]
