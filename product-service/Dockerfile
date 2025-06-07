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
WORKDIR /app
# The JAR for product-service will be in product-service/target/
COPY --from=builder /app/product-service/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
