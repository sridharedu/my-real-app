# Stage 1: Build the application
FROM maven:3.8-openjdk-17 AS builder

# Copy the entire project context
COPY . /app
WORKDIR /app

# Build the product-service module specifically
# Using mvn command directly as the base image has Maven installed.
# If we wanted to use mvnw, we'd need to ensure its path is correct relative to WORKDIR
# Running from the root, specifying the module:
RUN mvn -pl product-service -am package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jdk-alpine

# Install netcat (OpenBSD version often used on Alpine) for the wait script
RUN apk add --no-cache netcat-openbsd

WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/product-service/target/*.jar app.jar

# Copy the wait script (this script will be created in a subsequent plan step,
# and will be placed at the root of the product-service directory,
# which is the build context for this Dockerfile)
COPY wait-for-mongo.sh ./wait-for-mongo.sh
RUN chmod +x ./wait-for-mongo.sh

EXPOSE 8080
# Modified ENTRYPOINT to run the wait script before starting the app.
# The wait-for-mongo.sh script will be responsible for subsequently calling 'java -jar app.jar'.
ENTRYPOINT ["./wait-for-mongo.sh"]
